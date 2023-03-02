package com.backend.controllers;

import com.backend.appuser.AppUserRole;
import com.backend.repository.ProductRepository;
import com.backend.service.ProductService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
public class MainController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @RequestMapping({"", "/"})
    public String index(Model model){
        boolean userLogged = !(SecurityContextHolder.getContext() //legacy way, new showed in AppUsersController
                .getAuthentication() instanceof AnonymousAuthenticationToken);
        if((userLogged)){
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext()
                            .getAuthentication().getAuthorities();
            if(authorities.contains(
                    new SimpleGrantedAuthority(AppUserRole.SELLER.name()))){
                model.addAttribute("userLogged", "SELLER");
            }
            else if(authorities.contains(
                    new SimpleGrantedAuthority(AppUserRole.ADMIN.name()))){
                model.addAttribute("userLogged", "ADMIN");
            }
        }
        else
            model.addAttribute("userLogged", false);
        return "index";
    }


    @RequestMapping("/admin")
    public String adminPage(){
        return "/admin/admin";
    }

    @GetMapping("/test")
    public String testPage(){
        return "CustomError";
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@AuthenticationPrincipal UserDetails userDetails){
        if(userDetails != null){
            System.out.println("User already logged in!");
            return new ModelAndView("redirect:/");
        }
        System.out.println("New login request \n");
        return new ModelAndView("/users/login");
    }



}
