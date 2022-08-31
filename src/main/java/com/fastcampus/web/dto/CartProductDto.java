package com.fastcampus.web.dto;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.CartProduct;
import com.fastcampus.domain.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
public class CartProductDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long productId;

        public CartProduct toEntity(Cart cart, Product product) {
            return CartProduct.builder()
                    .cart(cart)
                    .product(product)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        
        @ApiModelProperty(value = "장바구니 아이디")
        private Long cartId;
        @ApiModelProperty(value = "멤버 아이디")
        private Long memberId;
        @ApiModelProperty(value = "상품 아이디")
        private Long productId;

    }
    
}
