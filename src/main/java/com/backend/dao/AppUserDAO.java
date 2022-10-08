package com.backend.dao;

import com.backend.dto.AppUserDTO;
import com.backend.dto.DTO;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

//database insertion v1.0
@Component
public class AppUserDAO implements DAO{
    private final String SQL_INSERT = "INSERT INTO users ()";
    private JdbcTemplate jdbcTemplate;
    private PreparedStatementCreator getByIdStatement;

    public DTO getById(Long Id){
        return null;
    }


    @Override
    public DTO getByEmail(String email) {
        DTO appUserDTO = new AppUserDTO();
        jdbcTemplate.execute("");
        return null;
    }

    /*@Override
    public void insert(AppUserDTO dto) {
        Long id = dto.getFirstName();
        jdbcTemplate.update("INSERT INTO " +
                "users (user_id, first_name, second_name, email, archive, password, app_user_role, locked, enabled) " +
                "VALUES ()");

    }*/

    @Override
    public void update(DTO dto) {

    }

    @Override
    public void delete(DTO dto) {

    }
}
