package com.fp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * CART에는 여러 PRODUCT를 담을 수 있고, PRODUCT 또한 여러 CART에 포함될 수 있다. 따라서 ManyToMany를 형성한다.
 * ManyToMany의 경우 정규화를 통해 1:N , N:1로 처리해야 한다.  따라서 중간테이블인 CartAndProduct라는 중간테이블을 생성해야 한다.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartAndProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

}
