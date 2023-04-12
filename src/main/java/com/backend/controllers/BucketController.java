package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.bucket.Bucket;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.repository.BucketRepository;
import com.backend.service.AppUserService;
import com.backend.service.BucketService;
import com.backend.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    private AppUserService appUserService;
    private BucketService bucketService;
    private ProductService productService;
    private Logger logger = LoggerFactory.getLogger(BucketController.class);
    @Autowired
    public BucketController(AppUserService appUserService,
                            BucketService bucketService,
                            ProductService productService){
        this.appUserService = appUserService;
        this.bucketService = bucketService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String showBucket(@AuthenticationPrincipal AppUser appUser){
        bucketService.getUsersBucket(appUser);

        return "tmp";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addProduct(@AuthenticationPrincipal AppUser appUser,
                                     @RequestParam("productId") String productId){
        if(appUser == null){
            logger.info("Error occurred while adding product in to the bucket (user is not logged in)");
            return ResponseEntity.badRequest().body("No user found");
        }
        Long prodId = Long.decode(productId);
        bucketService.addProduct(appUser, prodId);
        logger.info("Successfully added product {} in to the bucket", prodId);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<String> removeProduct(@AuthenticationPrincipal AppUser appUser,
                                                @RequestParam("productId") String productId){
        if(appUser == null){
            logger.info("Error while removing product, user not logged in");
            return ResponseEntity.badRequest().body("No user found");
        }

        Long prodId = Long.decode(productId);
        bucketService.removeProduct(appUser, prodId);
        logger.info("Successfully removed product {} from the bucket", prodId);
        return ResponseEntity.ok().build();
    }
}
