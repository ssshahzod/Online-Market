package com.backend.bucket;


import com.backend.appuser.AppUser;
import com.backend.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
    private Long size = 0L;

    @OneToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @ToString.Exclude
    private List<Product> products;

    public int addProduct(Product product){
        if(this.products == null){
            this.products = new ArrayList<>();
        }
        this.products.add(product);
        this.size++;
        return this.products.size();
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Product product : products) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }
}
