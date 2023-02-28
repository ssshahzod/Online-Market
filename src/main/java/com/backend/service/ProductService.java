package com.backend.service;

import com.backend.product.Product;
import com.backend.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public JSONArray getLastUploads(){
        return new JSONArray(productRepository.getNewest(5));
    }

    public void create(@NotNull Product product){
        productRepository.save(product);
    }

    public void update(String oldProductDesc, Product newProduct){
        Product tmp = productRepository.getProductByDescription(oldProductDesc);
        tmp.copy(newProduct);
        productRepository.save(tmp);

    }
}
