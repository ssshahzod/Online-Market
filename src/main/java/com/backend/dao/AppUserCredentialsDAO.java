package com.backend.dao;

import com.backend.dto.AppUserDTO.AppUserDTO;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserCredentialsDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserCredentialsDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private static long lastId = 100;

    @Autowired
    public AppUserCredentialsDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public AppUserDTO getByIdOrNull(Long Id){
        AppUserDTO appUserDTO;
        String str = "SELECT * FROM users_cred WHERE user_id = " + Id;
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
    public void insert(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user with Id: {}", lastId);
        String getLastId = "SELECT nextval('user_seq');";

        Optional<Long> val = Optional.ofNullable(jdbcTemplate.queryForObject(getLastId, Long.class));
        lastId = val.orElse(100L);
        lastId--;
        jdbcTemplate.update("INSERT  INTO " +
                "users_cred (user_id, password)" +
                "VALUES (?, ?);",
            lastId, dto.getPassword());
        lastId++;
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void update(@NotNull AppUserDTO dto) {
        //TODO: implement method
        AppUserDAOLogger.info("Update user details.\n");

        /*jdbcTemplate.update("UPDATE users SET password=?;",
                dto.getPassword());*/
    }

    @Override
    public void delete(Long id) {
        AppUserDAOLogger.info("Deleting user with id: {}", id);
        String sql = "DELETE FROM users_cred WHERE user_id=" + id;
        jdbcTemplate.execute(sql);
    }
}
