package com.backend.repository;

import com.backend.dao.DAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserCredentialsDAO implements DAO<AppUserDTO> {
    final Logger AppUserDAOLogger = LoggerFactory.getLogger(AppUserCredentialsDAO.class);

    private final JdbcTemplate jdbcTemplate;
    private static long lastId;

    public AppUserCredentialsDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public AppUserDTO getById(Long Id){
        AppUserDTO appUserDTO;
        String str = "SELECT * FROM users_cred WHERE id = " + Id;
        try{
            appUserDTO = jdbcTemplate.queryForObject(str, new BeanPropertyRowMapper<>(AppUserDTO.class));
            return appUserDTO;
        }
        catch(EmptyResultDataAccessException e){
            AppUserDAOLogger.info(e.getCause().toString());
            throw e;
        }
    }

    @Override
    public void insert(@NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Insert user credentials for (id): {}", lastId);
        String getLastId = "SELECT nextval('user_seq');";

        Optional<Long> val = Optional.ofNullable(jdbcTemplate.queryForObject(getLastId, Long.class));
        lastId = val.orElse(100L);
        lastId--;
        jdbcTemplate.update("INSERT INTO " +
                "users_cred (id, password)" +
                "VALUES (?, ?);",
            lastId, dto.getPassword());
        AppUserDAOLogger.info("New Id: {}", lastId);
    }

    @Override
    public void update(Long id, @NotNull AppUserDTO dto) {
        AppUserDAOLogger.info("Update user password.\n");
        jdbcTemplate.update("UPDATE users_cred SET password=? WHERE spring_shop.public.users_cred.id=?;",
                dto.getPassword(), id);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users_cred WHERE id=" + id;
        jdbcTemplate.execute(sql);
        AppUserDAOLogger.info("Deleted user with id: {}", id);
    }

}
