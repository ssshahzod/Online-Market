package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.bucket.Bucket;
import com.backend.dao.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.product.Product;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements com.backend.service.Service<AppUser> {
    private final Logger AppUserServiceLogger = LoggerFactory.getLogger(AppUserService.class);
    private final AppUserRepository appUserRepository;
    private final BucketRepository bucketRepository;

    public AppUserService(
                          AppUserRepository appUserRepository, BucketRepository bucketRepository){
        this.appUserRepository = appUserRepository;
        this.bucketRepository = bucketRepository;
    }

    @Override
    public void create(AppUser appUser) {
        AppUserServiceLogger.info("Create user with Id: {}", appUser.getId());
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser get(final String value) {
        return null;
    }

    @Override
    public Long getId(String value){
        return null;
    }
}
