package com.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BucketController {

    @GetMapping("/bucket")
    public String showBucket(){
        return "bucket";
    }
}
