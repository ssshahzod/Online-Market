package com.backend.dto.SellerDTO;

import com.backend.appuser.seller.Seller;
import com.backend.dto.DTO;
import com.backend.product.Product;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO implements DTO {

    private Long id;
    private List<Product> products;
    private String profileImage;
    private String sellerDescription;

    public SellerDTO(Seller seller){
        this.id = seller.getId();
        this.products = seller.getProducts();
        this.profileImage = seller.getProfileImage();
        this.sellerDescription = seller.getSellerDescription();
    }
}
