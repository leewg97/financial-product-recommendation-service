package com.fp.persistence;

import com.fp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupporterAmountGreaterThanEqualAndSupporterRegion(int supporterAmount,String region);

}
