package com.backend.controllers;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.service.AppUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

//TODO: dynamically generate home page
//TODO: pass 5 last added products to model and show it in home page
//TODO: multiple product class several times, so it fills parent container, and amount can be changed
//TODO: add email confirmation
//TODO: add username setting for registered users
//TODO: move application.yml properties to config class
//TODO: store information about expiration, activation etc from appUser in database
//TODO: password insertion and userInfo insertion are separated, so if one is getting error other still works

@Controller
@RequestMapping(value = "/users")
public class AppUsersController {
    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);
    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder (PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setAppUsersController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/sign")
    public ModelAndView signPage(){
        System.out.println("New sign request from the user \n");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth instanceof AnonymousAuthenticationToken)){
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("users/SignUp");
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("appuserdto") AppUserDTO appUserDTO, Model model){
            String encode = passwordEncoder.encode(appUserDTO.getPassword());
            appUserDTO.setPassword(encode);
            appUserService.create(appUserDTO);
            logger.info("New user with email: {} is signed.",
                                appUserDTO.getEmail());
            return "index";
    }

}
