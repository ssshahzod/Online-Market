package com.backend.product;

import com.backend.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {
    private static final String SEQ_NAME = "review_seq";
    @Id
    @SequenceGenerator(
            name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1
    )
    @GeneratedValue(
            generator = SEQ_NAME, strategy = GenerationType.IDENTITY
    )
    private Long id;
    private int score;
    private String review;

    @ManyToOne(

    )
    @JsonBackReference
    private AppUser appUser;

    @ManyToOne(

    )
    @JsonBackReference
    private Product product;
}
