package com.backend.registration;

import static org.assertj.core.api.Assertions.assertThat;

import com.backend.controllers.AppUsersController;
import com.backend.dao.AppUserDAO;
import com.backend.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppUserContextLoadTests {
    @Autowired
    private AppUsersController appUsersController;
    @Autowired
    private AppUserDAO appUserDAO;
    @Autowired
    private AppUserService appUserService;


    @Test
    void contextLoad() throws Exception{
        assertThat(appUsersController).isNotNull();
        assertThat(appUserDAO).isNotNull();
        assertThat(appUserService).isNotNull();
    }

}
