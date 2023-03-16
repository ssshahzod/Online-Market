package com.backend.product;

import com.backend.appuser.AppUser;
//import com.backend.appuser.seller.Seller;
import com.backend.appuser.seller.Seller;
import com.backend.bucket.Bucket;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private Long amount;
    private Date upload;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    private Seller seller;

    private String imageLink;

    public void copy(Product product){
        this.categories = product.categories;
        this.title = product.title;
        this.price = product.price;
        this.upload = product.upload;
        this.imageLink = product.imageLink;
        this.description = product.description;
    }

    public Product(String title, String description, float price, Long amount,
                   String imageLink, List<ProductCategory> categories){
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageLink = imageLink;
        this.categories = categories;
        this.amount = amount;
    }
    @ManyToMany
    private List<Bucket> bucket;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<ProductReview> reviews;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonBackReference
    /*
    @JoinTable(name = "categories",
            inverseJoinColumns = @JoinColumn(name = "category_id"))*/

    private List<ProductCategory> categories;
}
