package com.backend.bucket;


import com.backend.appuser.AppUser;
import com.backend.product.Product;
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
@Table(name = "buckets")
public class Bucket {
    private static final String SEQ_NAME = "bucket_seq";

    //using a shared primary key
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private AppUser appUser;

/*    @ManyToMany
    private List<Product> product;

    public Bucket(AppUser appUser, List<Product> products){
        this.appUser = appUser;
        this.product = products;
    }*/


}
