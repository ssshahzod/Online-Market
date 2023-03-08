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
    @Id
    @SequenceGenerator(name = SEQ_NAME,
                    allocationSize = 1,
                    sequenceName = SEQ_NAME)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = SEQ_NAME
    )
    private Long id;


    @OneToOne(mappedBy = "bucket")
    private AppUser appUser;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "bucket"
    )
    private List<Product> product;


}
