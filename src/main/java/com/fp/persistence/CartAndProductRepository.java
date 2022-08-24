package com.fp.persistence;

import com.fp.domain.CartAndProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartAndProductRepository extends JpaRepository<CartAndProduct, Long> {
}
