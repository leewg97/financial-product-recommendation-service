package com.fp.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ProductType 상품유형;

    private String 은행이름;

    private String 상품이름;

    private Long 금액;

    @ManyToOne
    @ToString.Exclude
    private Member member;

}
