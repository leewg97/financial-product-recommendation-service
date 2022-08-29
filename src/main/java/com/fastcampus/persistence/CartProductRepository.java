package com.fastcampus.persistence;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    CartProduct findByCartIdAndProductId(Long cartId, Long productId);

    List<CartProduct> findAllByCart(Cart cart);

}