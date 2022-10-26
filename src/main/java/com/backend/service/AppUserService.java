package com.backend.service;

import com.backend.dao.AppUserDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.DTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service<AppUserDTO> {
    private final AppUserDAO appUserDAO;
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);

    public AppUserService(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }

    @Override
    public void create(AppUserDTO appUserDTO) {
        AppUserServiceLogger.info("Insert user with email: {}", appUserDTO.getEmail());
        appUserDAO.insertOrUpdate(appUserDTO);
    }
}
