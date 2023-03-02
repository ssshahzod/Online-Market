package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
import com.backend.bucket.Bucket;
import com.backend.repository.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        AppUser appUser = new AppUser(appUserDTO);
        appUser.setRole(AppUserRole.USER.name());
        Bucket bucket = new Bucket();
        bucket.setAppUser(appUser);
        appUser.setBucket(bucket);
        try {
            appUserRepository.save(appUser);
            appUserCredentialsDAO.insert(appUserDTO);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }/*
        return 0L;*/
    }

    @Override
    public AppUserDTO get(final String value) {
        AppUser user = appUserRepository.getAppUserByEmail(value);
        if(user == null){
            throw new UsernameNotFoundException("There is no such user");
        }
        Optional<AppUserDTO> pass = Optional.of(appUserCredentialsDAO.getById(user.getId()));
        user.setPassword(pass.get().getPassword());
        return new AppUserDTO(user);
    }

    public AppUserDTO getById(Long id){
        return new AppUserDTO(appUserRepository.getReferenceById(id));
    }

    @Override
    public Long getId(String value){
        return appUserRepository.getIdByEmail(value);
    }
}
