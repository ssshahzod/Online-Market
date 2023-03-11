package com.backend.controllers;

import com.backend.appuser.AppUser;
import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.dto.SellerDTO.SellerDTO;
import com.backend.product.Product;
import com.backend.repository.AppUserRepository;
import com.backend.repository.CategoryRepository;
import com.backend.repository.ProductRepository;
import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/sellers")
@Controller
public class SellerController {

    final private AppUserService appUserService;
    final private ProductService productService;
    final private Logger logger = LoggerFactory.getLogger(SellerController.class);
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SellerController(AppUserService appUserService,
                            ProductService productService,
                            final ProductRepository productRepository,
                            final CategoryRepository categoryRepository){
        this.appUserService = appUserService;
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/{id}")
    public String getFullProfile(@PathVariable String id,
                                 @RequestParam(required = false) String state,
                                 Model model){
        Long userId = Long.decode(id);
        try {
            AppUser appUser = appUserService.getUserById(userId);
            SellerDTO seller = new SellerDTO(appUser.getSeller());
            seller.setProducts(productRepository.getProductsBySeller(appUser.getSeller()));
            model.addAttribute("userInfo", new AppUserDTO(appUser));
            model.addAttribute("sellerInfo", new SellerDTO(appUser.getSeller()));
            model.addAttribute("showNewProd", !(state == null));
        }
        catch(EntityNotFoundException e){
            logger.info("Couldn't find user for id: " + userId);
            return "CustomError";
        }
        return "sellers/profile";

    }

    @GetMapping("/{id}/new")
    public String uploadForm(@PathVariable String id,
                             Model model){
        Long userId = Long.decode(id);
        try {
            AppUserDTO appUserDTO = appUserService.getUserDTOById(userId);
        }
        catch(EntityNotFoundException e){
            return "CustomError";
        }
        model.addAttribute("id", id);
        return "sellers/NewProduct";
    }

    @PostMapping("/{id}/create")
    public String createProduct(@ModelAttribute("newProduct") Product product,
                              @PathVariable String id){
        Long sellerId = Long.decode(id);
        Date creationTime = new Date();
        product.setUpload(creationTime);
        product.setSeller(appUserService.getUserById(sellerId).getSeller());
        productService.create(product);
        return "sellers/profile";
    }
}
