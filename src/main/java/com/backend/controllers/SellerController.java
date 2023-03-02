package com.backend.controllers;

import com.backend.dto.AppUserDTO.AppUserDTO;
import com.backend.product.Product;
import com.backend.service.AppUserService;
import com.backend.service.ProductService;
import java.util.Date;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sellers")
@Controller
public class SellerController {

    final private AppUserService appUserService;
    final private ProductService productService;
    @Autowired
    public SellerController(AppUserService appUserService,
                            ProductService productService){
        this.appUserService = appUserService;
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProfile(@PathVariable String id,
                             Model model){
        Long userId = Long.decode(id);
        try {
            AppUserDTO appUserDTO = appUserService.getById(userId);
            if(appUserDTO.getRole().equals("SELLER"))
                model.addAttribute("userInfo", appUserDTO);
            else
                return "CustomError";
        }
        catch(EntityNotFoundException e){
            System.out.println("Couldn't find user for id: " + userId);
            return "CustomError";
        }
        return "sellers/profile";
    }


    @GetMapping("/{id}/new")
    public String uploadForm(@PathVariable String id){
        Long userId = Long.decode("0x" + id);
        try {
            AppUserDTO appUserDTO = appUserService.getById(userId);
        }
        catch(EntityNotFoundException e){
            return "CustomError";
        }
        return "sellers/NewProduct";
    }

    @PostMapping("/{id}/create")
    public void createProduct(@PathVariable String id,
                              @ModelAttribute("newProduct") Product product){
        Date creationTime = new Date();
        product.setUpload(creationTime);
        productService.create(product);
    }
}
