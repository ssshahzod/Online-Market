package com.backend.controllers;

import com.backend.dto.AppUserDTO;
import com.backend.service.appUserService.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new AppUserDTO());
        return "user";
    }

    @PostMapping
    public String saveUser(AppUserDTO appUserDTO, Model model){
        if(appUserService.save(appUserDTO)){
            return "redirect:/";
        }
        else{
            /*model.addAttribute("user", appUserDTO);
            return "user"; */
            return newUser(model);
        }
    }
}
