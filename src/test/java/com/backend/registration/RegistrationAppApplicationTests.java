package com.backend.registration;


import com.backend.controllers.AppUsersController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationAppApplicationTests {

    @Value("${spring.mvc.view.suffix}")
    private String suffix;
    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Autowired
    private AppUsersController appUsersController;

    @Test
    void parseYmlProperties() {
        Assertions.assertEquals(".html", suffix);
        Assertions.assertEquals("templates/", prefix);
    }

    @Test
    void databaseInsert(){


    }

}
