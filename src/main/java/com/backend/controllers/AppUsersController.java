package com.backend.controllers;

import com.backend.config.Config;
import com.backend.dao.AppUserDAO;
import com.backend.dto.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/users", method = RequestMethod.GET)
public class AppUsersController {
    @Value("${spring.mvc.view.prefix}")
    String prefix;
    @Value("${spring.mvc.view.suffix}")
    String suffix;

    final Logger logger = LoggerFactory.getLogger(AppUsersController.class);

    private final AppUserDAO appUserDAO;

    public AppUsersController(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }

    @GetMapping("/new")
    public String signPage(){
        logger.info("/users controller initialized with prefix:{} and suffix:{}", prefix, suffix);
        //return "users/signUp";
        return "index";
    }

    // get users page
     @GetMapping("/{id}")
     public String getUserById(@PathVariable("id") long id, Model model){
           DTO appUser = appUserDAO.getById(id);
           model.addAttribute(appUser);
           return "users/user";
     }


}
