package com.fastcampus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName; // 상품 이름

    private String productContent; // 상품 내용

    private String supporterName; // 지원 은행

    private String supporterRegion; // 지원 지역

    private int supporterAmount; // 지원 금액

}
