package com.backend.dto.ReviewDTO;

import com.backend.dto.DTO;
import com.backend.product.ProductReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO implements DTO {
    private String username;
    private int score;
    private String review;

    public ReviewDTO(ProductReview productReview){
        this.review = productReview.getReview();
        this.score = productReview.getScore();
        this.username = productReview.getAppUser().getFirstName() + " "
                                + productReview.getAppUser().getSecondName();
    }
}
