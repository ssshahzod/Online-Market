package com.backend.controllers;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import com.backend.service.AppUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

//TODO: build correct roles, configure spring security
//TODO: pass 5 last added products to model and show it in home page
//TODO: multiple product class several times, so it fills parent container, and amount can be changed
//TODO: add email confirmation
//TODO: add username setting for registered users
//TODO: move application.yml properties here
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

    @GetMapping("/login")
    public String loginPage(Model model){
        //logger.info("New login request. \n");
        System.out.println("New login request \n");
        AppUserDTO appUserDTO = new AppUserDTO();
        model.addAttribute("appUser", appUserDTO);
        return "users/login";
    }

    @PostMapping("/in")
    public RedirectView loginUser(@ModelAttribute("appUser") AppUserDTO loginCredentials,
                   Model model, RedirectAttributes redirectAttributes){
        AppUserDTO appUserDTO = appUserService.get(loginCredentials.getEmail());
        if(appUserDTO == null){
            model.addAttribute("error", "No user found with provided email.");
            return new RedirectView("/login");
        }
        else if(passwordEncoder.matches(loginCredentials.getPassword(), appUserDTO.getPassword())) {
            logger.info("User with email: {} successfully logged in ", loginCredentials.getEmail());
            model.addAttribute("Role", appUserDTO.getRole());
            model.addAttribute("userName", appUserDTO.getFirstName() + " "
                                                                        + appUserDTO.getSecondName());
            model.addAttribute("Signed", true);
            logger.info("Users role is:" + appUserDTO.getRole());
            return new RedirectView("/", true);

        }
        logger.info("Incorrect password");
        model.addAttribute("error", "Incorrect password!");
        return new RedirectView("/error");
    }

    @GetMapping("/sign")
    public String signPage(Model model){
        System.out.println("New sign request from the user \n");
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setArchived(false);
        model.addAttribute("appuserdto", appUserDTO);
        return "users/signUp";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("appuserdto") AppUserDTO appUserDTO, Model model){
            String encode = passwordEncoder.encode(appUserDTO.getPassword());
            appUserDTO.setPassword(encode);
            appUserService.create(appUserDTO);
            logger.info("New user with email: {} and role: {} is signed.",
                                appUserDTO.getEmail(), appUserDTO.getRole());

            model.addAttribute("newuser", appUserDTO);
            return "index";
    }

    @ModelAttribute("welcomeMsg")
    public String welcomeMsg(){
        return "Welcome to our website!";
    }


}
