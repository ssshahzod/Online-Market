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


    @Override
    public DTO getById(Long id) {
        DTO appUserDTO = new AppUserDTO();
        jdbcTemplate.execute("");
        return null;
    }

    @Override
    public void insert(DTO dto) {
        jdbcTemplate.update("INSERT INTO users () VALUES ()");

    }

    @Override
    public void update(DTO dto) {

    }

    @Override
    public void delete(DTO dto) {

    }
}
