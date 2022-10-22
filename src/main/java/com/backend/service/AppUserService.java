package com.backend.service;

import com.backend.dao.AppUserDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service {
    private final AppUserDAO appUserDAO;
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);

    public AppUserService(AppUserDAO appUserDAO){
        this.appUserDAO = appUserDAO;
    }

    @Override
    public void createUser(AppUserDTO appUserDTO){
        appUserDAO.insert(appUserDTO);
    }

}
