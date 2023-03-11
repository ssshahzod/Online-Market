package com.backend.service;

import com.backend.dto.ProductDTO.ProductDTO;
import com.backend.product.Product;
import com.backend.product.ProductCategory;
import com.backend.repository.CategoryRepository;
import com.backend.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getLastProducts(int limit){
        List<Product> products = productRepository.getNewest(limit);
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(product -> productDTOS.add(new ProductDTO(product)));
        return productDTOS;
    }

    public void setCategories(Product product, List<String> categoryNames){
        List<ProductCategory> categories = categoryRepository.findAll();
        product.setCategories(categories.stream()
                .filter(category -> categoryNames.contains(category.getCategoryName()))
                .collect(Collectors.toList()));

    }

    public List<ProductCategory> getCategories(){
        return categoryRepository.findAll();
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
