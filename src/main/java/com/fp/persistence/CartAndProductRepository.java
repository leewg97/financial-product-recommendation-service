package com.fp.persistence;

import com.fp.domain.CartAndProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartAndProductRepository extends JpaRepository<CartAndProduct, Long> {
}
