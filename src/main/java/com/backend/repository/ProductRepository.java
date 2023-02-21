package com.backend.repository;

import com.backend.product.Product;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products ORDER BY product_id DESC LIMIT :limit", nativeQuery = true)
    List<Product> getNewest(@Param("limit") int limit);

    Product getProductByDescription(@Param("description") String description);

    void update(@Param("newProduct") Product newProduct);
    List<Product> getAllByUploadOrderByUploadDesc(Date date);
}
