package com.backend.repository;

import com.backend.product.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    ;
}
