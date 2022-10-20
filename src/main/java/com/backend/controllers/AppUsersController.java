package com.backend.controllers;

import com.backend.dao.AppUserDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
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
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setFirstName("Alex");
        appUserDTO.setSecondName("Blac");
        appUserDTO.setPassword("asd");
        appUserDTO.setEmail("asd@mail.com");
        appUserDAO.insert(appUserDTO);
        logger.info("New user is signed!");
        //logger.info("New sign request from the user.\n");

        return "signUp";
    }

    @PostMapping()
    public String createUser(@ModelAttribute AppUserDTO appUserDTO, Model model){

            model.addAttribute("firstname", appUserDTO.getFirstName());
            model.addAttribute("secondname", appUserDTO.getSecondName());
            //AppUserDTO appUserDTO = new AppUserDTO();
            //appUserDTO.setFirstName(firstName);
            //appUserDTO.setSecondName(secondName);
            //appUserDTO.setPassword(password);
            //appUserDTO.setEmail(email);
            appUserDAO.insert(appUserDTO);
            logger.info("New user is signed!");

            //TODO: add username setting for registered users
            return "user";
    }


}
