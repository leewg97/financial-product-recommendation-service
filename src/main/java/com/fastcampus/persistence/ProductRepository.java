package com.fastcampus.persistence;

import com.fastcampus.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameContaining(String searchKeyword);

    List<Product> findByProductContentContaining(String searchKeyword);

}
