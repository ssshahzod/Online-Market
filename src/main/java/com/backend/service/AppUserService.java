package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.appuser.AppUserRole;
import com.backend.appuser.seller.Seller;
import com.backend.bucket.Bucket;
import com.backend.repository.AppUserCredentialsDAO;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import com.backend.repository.SellerRepository;
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
    private final SellerRepository sellerRepository;

    public AppUserService(AppUserCredentialsDAO appUserCredentialsDAO,
                          AppUserRepository appUserRepository, BucketRepository bucketRepository,
                          final SellerRepository sellerRepository){
        this.appUserCredentialsDAO = appUserCredentialsDAO;
        this.appUserRepository = appUserRepository;
        this.bucketRepository = bucketRepository;
        this.sellerRepository = sellerRepository;
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

    public AppUserDTO getUserDTOById(Long id){
        return new AppUserDTO(appUserRepository.getReferenceById(id));
    }

    public AppUser getUserById(Long id){
        return appUserRepository.getReferenceById(id);
    }

    @Override
    public Long getId(String value){
        return appUserRepository.getIdByEmail(value);
    }

    public void makeSeller(Long userId, Seller seller){
        AppUser appUser = appUserRepository.getReferenceById(userId);
        appUser.setSeller(seller);
        appUserRepository.save(appUser);
    }
}
