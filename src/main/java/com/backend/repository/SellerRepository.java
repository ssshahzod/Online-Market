package com.backend.repository;

import com.backend.appuser.AppUser;
import com.backend.appuser.seller.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller getSellerByAppUser(@Param("appUser") AppUser appUser);
}
