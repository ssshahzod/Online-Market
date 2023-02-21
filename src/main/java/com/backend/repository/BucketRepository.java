package com.backend.repository;

import com.backend.bucket.Bucket;
import com.backend.product.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

    Bucket getBucketById(Long id);

}
