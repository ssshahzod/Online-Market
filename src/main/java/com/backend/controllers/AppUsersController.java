package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import com.backend.service.AppUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//TODO: add page for users where they can become sellers
//TODO: add email confirmation

//TODO: add username setting for registered users
//TODO: store information about expiration, activation etc from appUser in database
//TODO: password insertion and userInfo insertion are separated, so if one is getting error other still works

@Controller
@RequestMapping(value = "/users")
public class AppUsersController {
    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);
    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public AppUsersController(final AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Autowired
    public void setPasswordEncoder (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setAppUsersController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/sign")
    public ModelAndView signPage(@AuthenticationPrincipal AppUser userDetails){
        System.out.println("New sign request from the user");
        return userDetails != null ? new ModelAndView("redirect:/") :
                new ModelAndView("users/SignUp");
    }

    @PostMapping("/new")
    public ModelAndView createUser(@ModelAttribute("appuserdto") AppUserDTO appUserDTO){
            String encode = passwordEncoder.encode(appUserDTO.getPassword());
            appUserDTO.setPassword(encode);
            appUserService.create(appUserDTO);
            logger.info("New user with email: {} is signed.", appUserDTO.getEmail());
            return new ModelAndView("redirect:/");
    }


/*    @GetMapping("/becomeSeller")
    public String becomeSel(){
        return "users/BecomeSeller";
    }

    @PostMapping("/update")
    public ModelAndView becomeSeller(Long userId,
                                     @ModelAttribute("seller") Seller seller){
        AppUser user = new AppUser(appUserService.getById(userId));
        user.setSeller(seller);
        appUserService.
    }*/

}
