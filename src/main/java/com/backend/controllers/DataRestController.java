package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.product.Product;
import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/data")
public class DataRestController {
    private final ProductService productService;
    private final AppUserService appUserService;
    @GetMapping("/id")
    public ResponseEntity<?> getUserId(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(appUserService.get(userDetails.getUsername()).getId());
    }

/*    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<List<Product>> getLastProducts(){
        List<Product> products = new ArrayList<>();
        Product product = new Product(
                100L,
                "Chair",
                "Its just a wooden chair",
                200F,
                new Date(),
                new Seller(),
                "https://novinkimebeli.ru/wp-content/uploads/2018/01/Stul-svoimi-rukami-12.jpg",
                null,
                null
        );
        products.add(product);

        return ResponseEntity.ok(products);
    }*/
}
