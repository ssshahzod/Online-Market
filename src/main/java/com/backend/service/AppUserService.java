package com.backend.service;

import com.backend.dao.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service<AppUserDTO> {
    private final AppUserCredentialsDAO appUserCredentialsDAO;
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);

    public AppUserService(AppUserCredentialsDAO appUserCredentialsDAO){
        this.appUserCredentialsDAO = appUserCredentialsDAO;
    }

    @Override
    public void create(AppUserDTO appUserDTO) {
        AppUserServiceLogger.info("Create user with email: {}", appUserDTO.getEmail());
        appUserCredentialsDAO.insert(appUserDTO);
    }

    @Override
    public AppUserDTO get(final String value) {
        return appUserCredentialsDAO.getByValueOrNull(value);
    }

    @Override
    public Long getId(String value){
        return appUserCredentialsDAO.getId(value);
    }
}
