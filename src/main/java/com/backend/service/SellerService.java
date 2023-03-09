package com.backend.service;

import com.backend.appuser.AppUser;
import com.backend.appuser.seller.Seller;
import com.backend.dto.SellerDTO.SellerDTO;
import com.backend.repository.AppUserRepository;
import com.backend.repository.ProductRepository;
import com.backend.repository.SellerRepository;

public class SellerService{
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;

    public SellerService(final SellerRepository sellerRepository,
                         final ProductRepository productRepository,
                         final AppUserRepository appUserRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
    }


    public SellerDTO getSellerDTOByAppUser(Long appUserId) {
        return new SellerDTO(sellerRepository.getReferenceById(appUserId));
    }

    public Seller getSellerByAppUser(final AppUser appUser) {
        Seller seller = sellerRepository.getSellerByAppUser(appUser);
        seller.setProducts(productRepository.getProductsBySeller(seller));
        return seller;
    }

    public void updateSellerImage(AppUser appUser, String imageLink){
        Seller seller = sellerRepository.getSellerByAppUser(appUser);
        seller.setProfileImage(imageLink);
        sellerRepository.save(seller);
        //appUserRepository.save(appUser); ??
    }

    public void updateSellerDesc(AppUser appUser, String description){
        Seller seller = sellerRepository.getSellerByAppUser(appUser);
        seller.setSellerDescription(description);
        sellerRepository.save(seller);
    }
}
