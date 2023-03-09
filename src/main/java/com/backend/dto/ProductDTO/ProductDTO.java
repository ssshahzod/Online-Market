package com.backend.dto.ProductDTO;

import com.backend.dto.DTO;
import com.backend.product.Product;
import com.backend.product.ProductCategory;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements DTO {
    private String title;
    private String description;
    private float price;
    private Date upload;
    private String imageLink;
    private List<ProductCategory> categories;

    public ProductDTO(Product product){
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.upload = product.getUpload();
        this.imageLink = product.getImageLink();
        this.categories = product.getCategories();
    }
}
