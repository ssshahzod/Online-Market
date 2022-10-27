package com.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.backend")
class AppUserServiceTest {

    @Autowired
    AppUserService appUserService;

    @Test
    void createUser() {
    }
}