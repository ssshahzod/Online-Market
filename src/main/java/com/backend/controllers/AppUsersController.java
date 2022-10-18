package com.backend.controllers;

import com.backend.dao.AppUserDAO;
import com.backend.dto.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/users", method = RequestMethod.GET)
public class AppUsersController {
    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);

    private final AppUserDAO appUserDAO;

    public AppUsersController(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @GetMapping("/new")
    public String signPage(){
        logger.info("New sign request from the user.\n");
        return "signUp";
    }



}
