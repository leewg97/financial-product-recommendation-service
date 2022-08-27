package com.fastcampus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID") // FK
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID") // FK
    private Product product;

    public void addBookmark(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

}
