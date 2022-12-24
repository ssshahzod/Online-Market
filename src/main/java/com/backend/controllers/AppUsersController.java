package com.backend.controllers;


import com.backend.appuser.Role;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/users")
public class AppUsersController {
    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);
    private final AppUserService appUserService;

    public AppUsersController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        logger.info("New login request. \n");
        AppUserDTO appUserDTO = new AppUserDTO();
        model.addAttribute("appUser", appUserDTO);
        return "users/login";
    }

    @PostMapping("/in")
    public String loginUser(@ModelAttribute("appUser") AppUserDTO loginCredentials,
                            Model model){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginCredentials.getEmail(), loginCredentials.getPassword());

        AppUserDTO appUserDTO = appUserService.get(loginCredentials.getEmail());
        if(appUserDTO == null){
            model.addAttribute("error", "No user found with provided email.");
            return "users/login";
        }
        else if(appUserService.get(loginCredentials.getEmail()).getPassword().equals(appUserDTO.getPassword())) {
            logger.info("User with email: {} successfully logged in ", loginCredentials.getEmail());
            model.addAttribute("Role", appUserDTO.getRole());
            model.addAttribute("userName", appUserDTO.getFirstName() + " "
                                                                        + appUserDTO.getSecondName());
            
            return "/";
        }
        model.addAttribute("error", "Incorrect password!");
        return "users/login";
    }

    @GetMapping("/sign")
    public String signPage(Model model){
        logger.info("New sign request from the user.\n");
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setRole(new Role());
        appUserDTO.setArchived(false);
        model.addAttribute("appuserdto", appUserDTO);
        return "users/signUp";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("appuserdto") AppUserDTO appUserDTO, Model model){
            appUserService.create(appUserDTO);
            logger.info("New user is signed!");
            //TODO: add username setting for registered users
            model.addAttribute("newuser", appUserDTO);
            return "user";
    }

    @ModelAttribute("welcomeMsg")
    public String welcomeMsg(){
        return "Welcome to our website!";
    }


}
