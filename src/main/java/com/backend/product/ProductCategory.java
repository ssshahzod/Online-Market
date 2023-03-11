package com.backend.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class ProductCategory {
    private final static String SEQ_NAME = "category_seq";

    @Id
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name",
            unique = true)
    private String categoryName;

    @Column(name = "category_desc",
            unique = true)
    private String categoryDescription;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "categories"
    )
    @JsonManagedReference
    private List<Product> products;

}
