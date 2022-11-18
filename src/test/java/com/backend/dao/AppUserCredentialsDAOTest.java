package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
//@ComponentScan("com.backend") <- try to run tests without this line
class AppUserCredentialsDAOTest {

    @Autowired
    private AppUserCredentialsDAO appUserCredentialsDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String mail = "mail@mail.com";
    private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", AppUserRole.USER, false);

    @Test
    void getByIdNull() {
        AppUserDTO appUserDTO;
        Long notExistingId = 0L;
        appUserDTO = appUserCredentialsDAO.getByIdOrNull(notExistingId);
        assertThat(appUserDTO).isNull();
    }

    @Test
    void insert(){
        AppUserDTO appUserDTO;
        String getId = "SELECT nextval('user_seq');";
        Optional<Long> val = Optional.ofNullable(jdbcTemplate.queryForObject(getId, Long.class));
        Long id = val.orElse(100L);

        assertThat(appUserCredentialsDAO.getByIdOrNull(id)).isNull();
        appUserCredentialsDAO.insert(underTest);
        appUserDTO = appUserCredentialsDAO.getByIdOrNull(id);
        assertThat(appUserDTO.getPassword()).isEqualTo(underTest.getPassword());
        appUserCredentialsDAO.delete(id);
    }

    @Test
    void update(){

    }

    @Test
    void delete(){
        String getId = "SELECT nextval('user_seq');";
        Optional<Long> val = Optional.ofNullable(jdbcTemplate.queryForObject(getId, Long.class));
        Long id = val.orElse(100L);
        assertThat(appUserCredentialsDAO.getByIdOrNull(id)).isNull();

        appUserCredentialsDAO.insert(underTest);
        assertThat(appUserCredentialsDAO.getByIdOrNull(id).getPassword()).isEqualTo(underTest.getPassword());

        appUserCredentialsDAO.delete(id);
        assertThat(appUserCredentialsDAO.getByIdOrNull(id)).isNull();
    }


}