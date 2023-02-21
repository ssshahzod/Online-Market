package com.backend.service;

import com.backend.bucket.Bucket;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.product.Product;
import com.backend.repository.BucketRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    private AppUserService appUserService;
    private BucketRepository bucketRepository;

    @Autowired
    public BucketService(AppUserService appUserService,
                         BucketRepository bucketRepository){
        this.appUserService = appUserService;
        this.bucketRepository = bucketRepository;
    }

    public Bucket getUsersBucket(AppUserDTO appUserDTO){
        return bucketRepository.getBucketById(appUserService.getId(appUserDTO.getEmail()));
    }
}
