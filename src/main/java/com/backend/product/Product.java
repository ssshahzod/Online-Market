package com.backend.product;

import com.backend.bucket.Bucket;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    private static final String SEQ_NAME = "product_seq";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @Column(name = "product_id")
    private Long id;

    private String title;
    private String description;
    private float price;
    private Date upload;

    private String imageLink;

    public void copy(Product product){
        this.categories = product.categories;
        this.title = product.title;
        this.price = product.price;
        //this.upload
        this.imageLink = product.imageLink;
        this.description = product.description;
    }
    @ManyToMany
    private List<Bucket> bucket;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<ProductCategory> categories;
}
