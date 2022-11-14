package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.bucket.Bucket;
import com.backend.dao.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import java.util.Optional;
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
        appUserDTO.setPassword("");
        AppUser appUser = new AppUser(appUserDTO);
        Bucket bucket = new Bucket();
        appUser.setBucket(bucket);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUserDTO get(final String value) {
        long id = appUserRepository.getIdByEmail(value);
        Optional<AppUserDTO> pass = Optional.ofNullable(appUserCredentialsDAO.getByIdOrNull(id));
        return pass.orElse(null);
    }

    @Override
    public Long getId(String value){
        return null;
    }
}
