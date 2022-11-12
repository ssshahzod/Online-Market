package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.backend")
class AppUserCredentialsDAOTest {

    @Autowired
    private AppUserCredentialsDAO appUserCredentialsDAO;

    private final String mail = "mail@mail.com";
    private AppUserDTO appUserDTO;
    private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", AppUserRole.USER, false);

    @Test
    void getByIdNull() {
        long id = 105;
        appUserDTO = appUserCredentialsDAO.getByIdOrNull(id);
        assertThat(appUserDTO).isNull();
    }


}