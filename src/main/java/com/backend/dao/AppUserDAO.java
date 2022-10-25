package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class AppUserDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private static long lastId = 100;

    public AppUserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUserDTO getByIdOrNull(Long Id){
        AppUserDTO appUserDTO;
        String str = "SELECT * FROM users WHERE user_id = " + Id;
        try{
            appUserDTO = jdbcTemplate.queryForObject(str, AppUserDTO.class);
            return appUserDTO;
        }
        catch(EmptyResultDataAccessException e){
            AppUserDAOLogger.debug(e.toString());
        }
        return null;
    }

    @Override
    public AppUserDTO getByEmailOrNull(String email) {
        AppUserDTO appUserDTO;
        String sql = "SELECT * FROM users WHERE email =" + email;
        try {
            appUserDTO = jdbcTemplate.queryForObject(sql, AppUserDTO.class);
            return appUserDTO;
        }
        catch(EmptyResultDataAccessException e){
            AppUserDAOLogger.debug(e.toString());
        }
        return null;
    }

    @Override
    public void insertOrUpdate(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user with Id: {}", lastId);
        lastId = jdbcTemplate.update("INSERT INTO " +
                "users (user_id, first_name, second_name, email, archive, password, app_user_role)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING user_id;",
            lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.isArchived(),
            dto.getPassword(), dto.getAppUserRole().name());
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void delete(AppUserDTO dto) {

    }
}
