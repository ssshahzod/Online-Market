package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.bucket.Bucket;
import com.backend.product.Product;
import com.backend.repository.AppUserRepository;
import com.backend.repository.BucketRepository;
import com.backend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BucketService(final AppUserService appUserService,
                         final AppUserRepository appUserRepository,
                         final BucketRepository bucketRepository,
                         final ProductRepository productRepository){
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    }

    public Bucket getUsersBucket(AppUser appUser){
        return bucketRepository.getByAppUser(appUser);
    }

    public void addProduct(AppUser user, Long productId){
        Product product = productRepository.getProductById(productId);
        AppUser appUser = appUserRepository.getReferenceById(user.getId());
        Bucket usersBucket = getUsersBucket(appUser);
        if(usersBucket == null){ //after right implementation it looks like there cant be null
            usersBucket = new Bucket();
            usersBucket.setAppUser(appUser);
            usersBucket.setId(appUserRepository.getBucketIdByAppUser(appUser.getId()));
        }
        usersBucket.addProduct(product);
        appUser.setBucket(usersBucket);

        appUserRepository.save(appUser);
    }

    public void removeProduct(AppUser user, Long productId){
        AppUser appUser = appUserRepository.getReferenceById(user.getId());
        Bucket usersBucket = getUsersBucket(appUser);
        var products = usersBucket.getProducts();

        List<Product> removedProducts = products
                .stream()
                .filter(product -> !product.getId().equals(productId))
                .collect(Collectors.toList());

        usersBucket.setProducts(removedProducts);
        usersBucket.setSize((long) removedProducts.size());
        appUserRepository.save(appUser);
    }
}
