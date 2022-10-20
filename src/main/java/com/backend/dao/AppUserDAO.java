package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

//database insertion v1.0
@Component
public class AppUserDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserDAO.class);

    private JdbcTemplate jdbcTemplate;
    private static long lastId = 0;


    public DTO getById(Long Id){
        AppUserDTO appUserDTO = new AppUserDTO();
        jdbcTemplate.execute("SELECT FROM users WHERE user_id = " + Id);

        return null;
    }


    @Override
    public AppUserDTO getByEmail(String email) {
        AppUserDTO appUserDTO = new AppUserDTO();
        jdbcTemplate.execute("");
        return appUserDTO;
    }

    @Override
    public void insert(AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user with Id: {}", lastId);
        //will value return?
        lastId =
                jdbcTemplate.update("INSERT INTO " +
                "users (user_id, first_name, second_name, email, archive, password, app_user_role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);",
            lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.isArchived(),
            dto.getPassword(), dto.getAppUserRole());
        AppUserDAOLogger.info("New lastId after insertion: {}", lastId);
    }

    @Override
    public void update(AppUserDTO dto) {

    }

    @Override
    public void delete(AppUserDTO dto) {

    }
}
