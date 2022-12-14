package com.fastcampus.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Cart {

    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    private List<CartProduct> cartProducts = new ArrayList<>();

    public static Cart addCart(Member member){
        Cart cart = new Cart();
        cart.setMember(member);
        return cart;
    }

}
