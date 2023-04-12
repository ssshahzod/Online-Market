package com.backend.dao;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.appuser.AppUserRole;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserCredentialsDAO;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@ComponentScan("com.backend")
class AppUserCredentialsDAOTest {

    @Mock
    private AppUserCredentialsDAO appUserCredentialsDAO;
    @MockBean
    private JdbcTemplate jdbcTemplate;

    private final String mail = "mail@mail.com";
    private final AppUserDTO underTest = new AppUserDTO(10L, "Alise", "Lise", mail,
            "asd", AppUserRole.USER.name(), false);



    @Test
    void givenNoUsers_getById_returnNull() {

        when(jdbcTemplate.queryForObject(anyString(), Long.class)).thenReturn(null);
        Long notExistingId = 0L;

        AppUserDTO appUserDTO = appUserCredentialsDAO.getById(notExistingId);

        verify(appUserCredentialsDAO, times(1)).getById(any());
        assertThat(appUserDTO).isNull();
    }

    @Test
    void insert(){

        when(jdbcTemplate.update(anyString())).thenReturn(0);
        AppUserDTO appUserDTO;
        InOrder inOrder = inOrder(jdbcTemplate);


        appUserCredentialsDAO.insert(underTest);


        inOrder.verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Long.class));
        inOrder.verify(jdbcTemplate, times(1)).update(anyString());
        verify(jdbcTemplate, times(1)).update(anyString());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), eq(Long.class));
    }

    @Test
    void delete() {}




}
