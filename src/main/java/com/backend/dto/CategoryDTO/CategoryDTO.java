package com.backend.dto.CategoryDTO;

import com.backend.dto.DTO;
import com.backend.product.ProductCategory;
import com.backend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO implements DTO {
    private String categoryName;
    private String categoryDescription;

    public CategoryDTO(ProductCategory productCategory){
        this.categoryDescription = productCategory.getCategoryDescription();
        this.categoryName = productCategory.getCategoryName();
    }
}
