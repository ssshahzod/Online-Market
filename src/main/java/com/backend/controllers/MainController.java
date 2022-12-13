package com.backend.controllers;

import com.backend.product.Product;
import com.backend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;


    @RequestMapping({"", "/"})
    public String index(Model model){
        /*List<Product> products = new ArrayList<Product>(10);
        products = productRepository.findAll();
        for(int i = 0; i < 10; i++){
            products.
            model.addAttribute(i, product);
        }*/
        return "index";
    }

    @RequestMapping("/admin")
    public String adminPage(){
        return "/admin";
    }


}
