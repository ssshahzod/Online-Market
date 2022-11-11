package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.dao.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service<AppUserDTO> {
    private final AppUserCredentialsDAO appUserCredentialsDAO;
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserCredentialsDAO appUserCredentialsDAO, AppUserRepository appUserRepository){
        this.appUserCredentialsDAO = appUserCredentialsDAO;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public void create(AppUserDTO appUserDTO) {
        AppUserServiceLogger.info("Create user with email: {}", appUserDTO.getEmail());
        appUserCredentialsDAO.insert(appUserDTO);

        AppUser appUser = new AppUser(appUserDTO);
        appUserRepository.save(appUser);
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
