package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.dto.ProductDTO.ProductDTO;
import com.backend.product.ProductCategory;
import com.backend.service.AppUserService;
import com.backend.service.BucketService;
import com.backend.service.ProductService;
import java.util.List;
import java.util.stream.Collectors;
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
    private final BucketService bucketService;
    @GetMapping("/id")
    public ResponseEntity<?> getUserId(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(appUserService.get(userDetails.getUsername()).getId());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getLastProducts(){
        List<ProductDTO> productDTOS = productService.getLastProducts(5);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/getUsersBucket")
    public ResponseEntity<List<ProductDTO>> getUsersBucket(@AuthenticationPrincipal AppUser appUser){
        var productDTOS = bucketService.getUsersBucket(appUser)
                .getProducts()
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<ProductDTO>> getUserRecomendations(){
        //List<ProductDTO> productDTOS = productService.get
        return null;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getCategories(){
        List<ProductCategory> categories = productService.getCategories();
        return ResponseEntity.ok(categories);
    }

    /*@GetMapping("/getsellerproducts")
    public ResponseEntity<List<ProductDTO>> getSellersProducts(){
        var productDTOS = productService.getProductsDTOSBySeller();
        return ResponseEntity.ok(productDTOS);
    }*/
}
