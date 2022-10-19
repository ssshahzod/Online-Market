package com.backend.controllers;

import com.backend.dao.AppUserDAO;
import com.backend.dto.DTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SingleUserController {

    private final AppUserDAO appUserDAO;

    public SingleUserController(AppUserDAO appUserDAO){
      this.appUserDAO = appUserDAO;
    }

    /*@GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model){
      DTO appUser = appUserDAO.getById(id);
      model.addAttribute(appUser);
      return "/users/user";
    }*/
}
