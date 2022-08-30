package com.fastcampus.persistence;

import com.fastcampus.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findById(Long id);

    Cart findByMemberId(Long memberId);

}
