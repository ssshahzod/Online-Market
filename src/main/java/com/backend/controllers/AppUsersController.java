package com.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AppUsersController {



    @GetMapping("/new")
    public String newUser(){
        return null;
    }

    /* get users page
    * @GetMapping("/{id}")
    * public String getUserById(@PathVariable"id" long id, Model model){
    *       return "user";
    * }
    * */

}
