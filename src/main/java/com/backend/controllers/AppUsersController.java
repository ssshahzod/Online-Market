package com.backend.controllers;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @GetMapping("/sign")
    public String signPage(Model model){
        logger.info("New sign request from the user.\n");
        AppUserDTO appUserDTO = new AppUserDTO();
        model.addAttribute("appuserdto", appUserDTO);
        return "signUp";
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
