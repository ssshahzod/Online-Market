package com.backend.controllers;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateNewUserController {
    private final AppUserService appUserService;

    public CreateNewUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/n")
    public String createUser(@ModelAttribute("appuserdto") AppUserDTO appUserDTO, Model model){
        appUserService.create(appUserDTO);
        //logger.info("New user is signed!");
        //TODO: add username setting for registered users
        model.addAttribute("newuser", appUserDTO);
        return "users/user";
    }

}
