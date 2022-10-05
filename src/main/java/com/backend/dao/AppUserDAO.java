package com.backend.dao;

import com.backend.dto.AppUserDTO;
import com.backend.dto.DTO;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;


public class AppUserDAO implements DAO{
    private final DTO appUserDTO;

    public AppUserDAO(AppUserDTO appUserDTO){
        this.appUserDTO = appUserDTO;
    }

    @Override
    public DTO get() {
        DTO appUserDTO = new AppUserDTO();
        
        return null;
    }

    @Override
    public void insert(DTO dto) {

    }

    @Override
    public void update(DTO dto) {

    }

    @Override
    public void delete(DTO dto) {

    }
}
