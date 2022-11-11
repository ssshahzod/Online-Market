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
    void insert(){
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(underTest.getEmail());
        assertThat(appUserDTO).isNull();

        appUserCredentialsDAO.insert(underTest);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(underTest.getEmail());
        assertThat(appUserDTO).isEqualTo(underTest);

        appUserCredentialsDAO.delete(underTest);
    }

    @Test
    void update(){
        appUserCredentialsDAO.insert(underTest);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(underTest.getEmail());
        assertThat(appUserDTO).isEqualTo(underTest);

        String tmpMail = "yandex@mail.mail";
        AppUserDTO tmp = new AppUserDTO("A", "B", tmpMail, "asd", AppUserRole.USER, false);
        appUserCredentialsDAO.update(tmp);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(tmpMail);
        assertThat(appUserDTO).isEqualTo(tmp);
    }

    @Test
    void getByEmail() {
        appUserCredentialsDAO.insert(underTest);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(mail);
        assertThat(appUserDTO).isEqualTo(underTest);
        appUserCredentialsDAO.delete(underTest);
    }

    @Test
    void delete(){
        AppUserDTO tmp = new AppUserDTO("Mark", "Two", "yandex@mail",
                "asd", AppUserRole.USER, false);
        appUserCredentialsDAO.insert(tmp);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(tmp.getEmail());
        assertThat(appUserDTO).isEqualTo(tmp);
        appUserCredentialsDAO.delete(tmp);
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(tmp.getEmail());
        assertThat(appUserDTO).isNull();

    }

    @Test
    void getByIdNull() {
        long id = 105;
        appUserDTO = appUserCredentialsDAO.getByIdOrNull(id);
        assertThat(appUserDTO).isNull();
    }

    @Test
    void getByEmailNull(){
        appUserDTO = appUserCredentialsDAO.getByValueOrNull(mail + "tmp");
        assertThat(appUserDTO).isEqualTo(null);
    }

}