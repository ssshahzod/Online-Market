package com.backend.appuser;

import static org.assertj.core.api.Assertions.assertThat;

import com.backend.controllers.AppUsersController;
import com.backend.repository.AppUserCredentialsDAO;
import com.backend.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppUserContextLoadTests {
    @Autowired
    private AppUsersController appUsersController;
    @Autowired
    private AppUserCredentialsDAO appUserCredentialsDAO;
    @Autowired
    private AppUserService appUserService;


    @Test
    void contextLoad() throws Exception{
        assertThat(appUsersController).isNotNull();
        assertThat(appUserCredentialsDAO).isNotNull();
        assertThat(appUserService).isNotNull();
    }

}
