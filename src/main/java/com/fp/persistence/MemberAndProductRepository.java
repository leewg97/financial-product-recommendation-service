package com.fp.persistence;

import com.fp.domain.MemberAndProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberAndProductRepository extends JpaRepository<MemberAndProduct, Long> {
}
