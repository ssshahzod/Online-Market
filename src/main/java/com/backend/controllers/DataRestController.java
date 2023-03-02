package com.backend.controllers;

import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/data")
public class DataRestController {
    private final ProductService productService;
    private final AppUserService appUserService;
    @GetMapping("/id")
    public ResponseEntity<?> getUserId(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(appUserService.get(userDetails.getUsername()).getId());
    }
}
