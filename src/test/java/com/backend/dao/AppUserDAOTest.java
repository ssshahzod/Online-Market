package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ComponentScan("com.backend")
class AppUserDAOTest {

    @Autowired
    private AppUserDAO appUserDAO;

    private final String mail = "mail@mail.com";
    private AppUserDTO appUserDTO;
    private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", AppUserRole.USER, false);

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void insertOrUpdate(){
        appUserDAO.insertOrUpdate(underTest);
    }

    @Test
    void getByEmail() {
        appUserDTO = appUserDAO.getByEmailOrNull(mail);
        assertThat(appUserDTO).isEqualTo(underTest);
    }

    @Test
    void getByIdNull() {
        long id = 105;
        appUserDTO = appUserDAO.getByIdOrNull(id);
        assertThat(appUserDTO).isNull();
    }


}