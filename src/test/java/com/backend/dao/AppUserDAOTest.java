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
class AppUserDAOTest {

    @Autowired
    private AppUserDAO appUserDAO;

    private final String mail = "mail@mail.com";
    private AppUserDTO appUserDTO;
    private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", AppUserRole.USER, false);

    @Test
    void insertOrUpdate(){
        appUserDTO = appUserDAO.getByValueOrNull(underTest.getEmail());
        assertThat(appUserDTO).isNull();

        appUserDAO.insertOrUpdate(underTest);
        appUserDTO = appUserDAO.getByValueOrNull(underTest.getEmail());
        assertThat(appUserDTO).isEqualTo(underTest);

        appUserDAO.delete(underTest);
    }

    @Test
    void getByEmail() {
        appUserDAO.insertOrUpdate(underTest);
        appUserDTO = appUserDAO.getByValueOrNull(mail);
        assertThat(appUserDTO).isEqualTo(underTest);
        appUserDAO.delete(underTest);
    }

    @Test
    void delete(){
        AppUserDTO tmp = new AppUserDTO("Mark", "Two", "yandex@mail",
                "asd", AppUserRole.USER, false);
        appUserDAO.insertOrUpdate(tmp);
        appUserDTO = appUserDAO.getByValueOrNull(tmp.getEmail());
        assertThat(appUserDTO).isEqualTo(tmp);
        appUserDAO.delete(tmp);
        appUserDTO = appUserDAO.getByValueOrNull(tmp.getEmail());
        assertThat(appUserDTO).isNull();

    }

    @Test
    void getByIdNull() {
        long id = 105;
        appUserDTO = appUserDAO.getByIdOrNull(id);
        assertThat(appUserDTO).isNull();
    }

    @Test
    void getByEmailNull(){
        appUserDTO = appUserDAO.getByValueOrNull(mail + "tmp");
        assertThat(appUserDTO).isEqualTo(null);
    }

}