package com.backend.controllers;

import com.backend.dao.AppUserDAO;
import com.backend.dto.DTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AppUsersController {
    private final AppUserDAO appUserDAO;

    public AppUsersController(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }


    @PostMapping("/new")
    public String newUser(){
        return null;
    }

    // get users page
     @GetMapping("/{id}")
     public String getUserById(@PathVariable("id") long id, Model model){
           DTO appUser = appUserDAO.getById(id);
           model.addAttribute(appUser);
           return "user";
     }


}
