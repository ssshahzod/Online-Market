package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

//database insertion v1.0
@Component
public class AppUserDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private static long lastId = 100;

    public AppUserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public DTO getById(Long Id){
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
    public AppUserDTO getByEmail(String email) {
        AppUserDTO appUserDTO = new AppUserDTO();
        jdbcTemplate.execute("");
        return appUserDTO;
    }

    @Override
    public void insert(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user with Id: {}", lastId);
        lastId = jdbcTemplate.update("INSERT INTO " +
                "users (user_id, first_name, second_name, email, archive, password, app_user_role)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING user_id;",
            lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.isArchived(),
            dto.getPassword(), dto.getAppUserRole().name());
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void update(AppUserDTO dto) {
        
    }

    @Override
    public void delete(AppUserDTO dto) {

    }
}
