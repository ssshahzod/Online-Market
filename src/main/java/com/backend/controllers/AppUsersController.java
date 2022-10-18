package com.backend.controllers;

import com.backend.dao.AppUserDAO;
import com.backend.dto.AppUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/users")
public class AppUsersController {
    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);

    private final AppUserDAO appUserDAO;

    public AppUsersController(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @GetMapping("/sign")
    public String signPage(){
        logger.info("New sign request from the user.\n");

        return "signUp";
    }

    @PostMapping("/new")
    public String createUser(@RequestParam("firstname") String firstName, @RequestParam("secondname") String secondName,
                             @RequestParam("password") String password, @RequestParam("matchingpassword") String matchingPassword,
                             @RequestParam("email") String email, Model model){

        if(password.equals(matchingPassword)){
            model.addAttribute("firstname", firstName);
            model.addAttribute("secondname", secondName);
            AppUserDTO appUserDTO = new AppUserDTO();
            appUserDTO.setFirstName(firstName);
            appUserDTO.setSecondName(secondName);
            appUserDTO.setPassword(password);
            appUserDTO.setEmail(email);
            appUserDAO.insert(appUserDTO);
            logger.info("New user is signed!");

            //TODO: add username setting for registered users
            return "user";
        }
        else{
            model.addAttribute("errorMessage", "Passwords do not match!");
            return "signUp";
        }
    }


}
