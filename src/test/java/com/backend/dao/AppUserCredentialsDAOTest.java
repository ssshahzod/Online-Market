package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserCredentialsDAO;
import java.util.Optional;
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
    /*private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", new Role(), false);*/
    private final AppUserDTO underTest = new AppUserDTO("Alise", "Lise", mail,
            "asd", AppUserRole.USER.name(), false);

    @Test
    void getByIdNull() {
        AppUserDTO appUserDTO;
        Long notExistingId = 0L;
        appUserDTO = appUserCredentialsDAO.getById(notExistingId);
        assertThat(appUserDTO).isNull();
    }

    @Test
    void insert(){
        AppUserDTO appUserDTO;
        String getId = "SELECT nextval('user_seq');";
        Optional<Long> val = Optional.ofNullable(jdbcTemplate.queryForObject(getId, Long.class));
        Long id = val.orElse(100L);

        assertThat(appUserCredentialsDAO.getById(id)).isNull();
        appUserCredentialsDAO.insert(underTest);
        appUserDTO = appUserCredentialsDAO.getById(id);
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
        assertThat(appUserCredentialsDAO.getById(id)).isNull();

        appUserCredentialsDAO.insert(underTest);
        assertThat(appUserCredentialsDAO.getById(id).getPassword()).isEqualTo(underTest.getPassword());

        appUserCredentialsDAO.delete(id);
        assertThat(appUserCredentialsDAO.getById(id)).isNull();
    }


}