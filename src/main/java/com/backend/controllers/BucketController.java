package com.backend.controllers;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.BucketRepository;
import com.backend.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BucketController {
    private AppUserService appUserService;
    private BucketRepository bucketRepository;
    @Autowired
    public BucketController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @GetMapping("/bucket")
    public String showBucket(@AuthenticationPrincipal AppUserDTO appUserDTO){
        appUserService.getId(appUserDTO.getEmail());
        return "/bucket/bucket";
    }
}
