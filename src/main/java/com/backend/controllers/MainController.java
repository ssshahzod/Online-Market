package com.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"", "/"})
    public String index(){
        return "index";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }


}
