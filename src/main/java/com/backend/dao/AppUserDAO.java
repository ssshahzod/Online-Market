package com.backend.dao;

import com.backend.appuser.AppUser;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class AppUserDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private AppUser appUser;
    private static long lastId = 100;

    public AppUserDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUserDTO getByIdOrNull(Long Id){
        AppUserDTO appUserDTO;
        String str = "SELECT * FROM users WHERE user_id = " + Id;
        try{
            appUserDTO = jdbcTemplate.queryForObject(str, new BeanPropertyRowMapper<AppUserDTO>(AppUserDTO.class));
            return appUserDTO;
        }
        catch(EmptyResultDataAccessException e){
            AppUserDAOLogger.debug(e.toString());
        }
        return null;
    }

    @Override
    public AppUserDTO getByValueOrNull(String email) {
        AppUserDTO appUserDTO;
        String sql = "SELECT * FROM users WHERE email='" + email + "'";
        try {
            appUserDTO = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<AppUserDTO>(AppUserDTO.class));
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
        String getLastId = "SELECT max(user_id) FROM users;";
        Long value = jdbcTemplate.queryForObject(getLastId, Long.class);
        if(value != null){
            lastId = value + 1;
        }

        jdbcTemplate.update("INSERT  INTO " +
                "users (user_id, first_name, second_name, email, archive, password, app_user_role)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ON CONFLICT (email) DO UPDATE SET password = EXCLUDED.password;",
            lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.isArchived(),
            dto.getPassword(), dto.getAppUserRole().name());
        lastId++;
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void delete(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Deleting user with firstname: {} and mail: {}", dto.getFirstName(), dto.getEmail());
        String sql = "DELETE FROM users WHERE first_name='" + dto.getFirstName() + "'" + "AND second_name="
                + "'" + dto.getSecondName() + "'" + "AND email=" + "'" + dto.getEmail() + "'";
        jdbcTemplate.execute(sql);
    }
}
