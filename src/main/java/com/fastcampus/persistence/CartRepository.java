package com.fastcampus.persistence;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findById(Long id);

    List<Cart> findByMember(Member member);
}
