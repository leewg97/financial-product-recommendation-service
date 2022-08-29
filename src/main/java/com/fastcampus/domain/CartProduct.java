package com.fastcampus.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CART_PRODCUT")
@Entity
@Builder
public class CartProduct {

    @Id
    @Column(name = "cart_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public static CartProduct addCartProduct(Cart cart, Product product) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        return cartProduct;
    }

}
