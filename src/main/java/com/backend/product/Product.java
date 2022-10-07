package com.backend.product;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<ProductCategory> categories;
}
