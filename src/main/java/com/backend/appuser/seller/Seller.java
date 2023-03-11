package com.backend.appuser.seller;

import com.backend.appuser.AppUser;
import com.backend.dto.SellerDTO.SellerDTO;
import com.backend.product.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sellers")
public class Seller {
    private final static String SEQ_NAME = "seller_seq";
    @Id
    @SequenceGenerator(name = SEQ_NAME,
            allocationSize = 1,
            sequenceName = SEQ_NAME)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SEQ_NAME
    )
    private Long id;

    @OneToOne(
            mappedBy = "seller"
    )
    @JsonManagedReference
    private AppUser appUser;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product> products;

    private String profileImage;
    private String sellerDescription;

    public Seller(final AppUser appUser,
           final List<Product> products,
           final String profileImage,
           final String sellerDescription) {
        this.appUser = appUser;
        this.products = products;
        this.profileImage = profileImage;
        this.sellerDescription = sellerDescription;
    }

    public Seller(SellerDTO sellerDTO){
        this.id = sellerDTO.getId();
        this.sellerDescription = sellerDTO.getSellerDescription();
        this.profileImage = sellerDTO.getProfileImage();
        this.products = sellerDTO.getProducts();
    }
}
