package com.fp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productType; // 상품 타입

    private String productName; // 상품 이름

    private String supporterName; // 지원 은행

    private String supporterRegion; // 지원 지역

    private Long supporterAmount; // 지원 금액

    // 1 : N 관계
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<MemberAndProduct> memberAndProducts = new ArrayList<>();

}
