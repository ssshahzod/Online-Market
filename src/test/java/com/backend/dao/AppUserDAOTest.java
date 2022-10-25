package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AppUserDAOTest {

    @Mock
    private AppUserDAO appUserDAO;
    private AutoCloseable autoCloseable;
    private final String mail = "mail@mail.com";
    private AppUserDTO appUserDTO;
    private final AppUserDTO underTest = new AppUserDTO("Alise", "LL", mail,
            "asd", AppUserRole.USER, false);

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void insertOrUpdate(){
        appUserDAO.insertOrUpdate(underTest);
    }

    @Test
    void getByEmailOrNull() {
        appUserDTO = appUserDAO.getByEmailOrNull(mail);
        assertThat(appUserDTO).isEqualTo(underTest);
    }

    @Test
    void getByIdOrNull() {
        long id = 105;
        appUserDTO = appUserDAO.getByIdOrNull(id);
        assertThat(appUserDTO).isNull();
    }


}