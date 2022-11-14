package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.bucket.Bucket;
import com.backend.dao.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service<AppUserDTO> {
    private final AppUserCredentialsDAO appUserCredentialsDAO;
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);
    private final AppUserRepository appUserRepository;
    private final BucketRepository bucketRepository;

    public AppUserService(AppUserCredentialsDAO appUserCredentialsDAO,
                          AppUserRepository appUserRepository, BucketRepository bucketRepository){
        this.appUserCredentialsDAO = appUserCredentialsDAO;
        this.appUserRepository = appUserRepository;
        this.bucketRepository = bucketRepository;
    }

    @Override
    public void create(AppUserDTO appUserDTO) {
        AppUserServiceLogger.info("Create user with email: {}", appUserDTO.getEmail());
        AppUser appUser = new AppUser(appUserDTO);
        Bucket bucket = new Bucket(appUser.getId(), appUser, new ArrayList<>(0));
        bucketRepository.save(bucket);
        appUser.setBucket(bucket);
        appUserRepository.save(appUser);
        appUserCredentialsDAO.insert(appUserDTO);
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
