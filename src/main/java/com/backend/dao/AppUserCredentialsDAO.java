package com.backend.dao;

import com.backend.appuser.AppUser;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserCredentialsDAO implements DAO<AppUserDTO>{
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserCredentialsDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private AppUser appUser;
    private static long lastId = 100;

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

    public long getId(String email){
        Long id;
        String str = "SELECT user_id FROM users_cred WHERE email='" + email + "'";
        try{
            id = jdbcTemplate.queryForObject(str, Long.class);
            if(id != null){
                return id;
            }
        }
        catch (EmptyResultDataAccessException e){
            AppUserDAOLogger.debug(e.toString());
        }
        return 0;
    }

    @Override
    public AppUserDTO getByValueOrNull(String email) {
        AppUserDTO appUserDTO;
        String sql = "SELECT * FROM users_cred WHERE email='" + email + "'";
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
    public void insert(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user with Id: {}", lastId);
        String getLastId = "SELECT max(user_id) FROM users_cred;";
        Long value = jdbcTemplate.queryForObject(getLastId, Long.class);
        if(value != null){
            lastId = value + 1;
        }

        jdbcTemplate.update("INSERT  INTO " +
                "users_cred (user_id, first_name, second_name, email, archive, password, app_user_role)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?) ON CONFLICT (email) DO NOTHING;",
            lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.isArchived(),
            dto.getPassword(), dto.getAppUserRole().name());
        lastId++;
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void update(@NotNull AppUserDTO dto) {
        //TODO: implement method
        AppUserDAOLogger.info("Update user details.\n");

        /*jdbcTemplate.update("UPDATE users SET first_name=?, second_name=?, email=?, password=?,"
                        + "archive=? ,app_user_role=? ;",
                lastId, dto.getFirstName(), dto.getSecondName(), dto.getEmail(), dto.getPassword(),
                dto.isArchived(), dto.getAppUserRole().name());*/
    }

    @Override
    public void delete(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Deleting user with firstname: {} and mail: {}", dto.getFirstName(), dto.getEmail());
        String sql = "DELETE FROM users_cred WHERE first_name='" + dto.getFirstName() + "'" + "AND second_name="
                + "'" + dto.getSecondName() + "'" + "AND email=" + "'" + dto.getEmail() + "'";
        jdbcTemplate.execute(sql);
    }
}
