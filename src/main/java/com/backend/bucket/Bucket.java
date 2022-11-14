package com.backend.bucket;


import com.backend.appuser.AppUser;
import com.backend.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Tables;

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
    @Column(name = "user_id")
    private Long id;

    @OneToOne(mappedBy = "bucket", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AppUser appUser;

    @ManyToMany
    private List<Product> product;


}
