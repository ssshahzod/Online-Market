package com.backend.repository;

import com.backend.product.ProductCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findByCategoryName(@Param("name") String name);
}
