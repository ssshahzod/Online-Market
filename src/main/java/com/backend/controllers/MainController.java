package com.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    //TODO: pass 5 last added products to model and show it in home page
    //private ProductRepository productRepository;

    @RequestMapping({"", "/"})
    public String index(){

        return "index";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }


}
