package com.backend.dto.ProductDTO;

import com.backend.dto.CategoryDTO.CategoryDTO;
import com.backend.dto.DTO;
import com.backend.product.Product;
import com.backend.product.ProductCategory;
import com.backend.product.ProductReview;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements DTO {
    private Long id;
    private String title;
    private String description;
    private float price;
    private Date upload;
    private String imageLink;
    private List<CategoryDTO> categories;

    private List<ProductReview> productReviews;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.upload = product.getUpload();
        this.imageLink = product.getImageLink();
        this.productReviews = product.getReviews();
        this.categories = product.getCategories()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }
}
