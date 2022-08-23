package com.fp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1 : N 관계
    @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
    private List<CartAndProduct> cartAndProducts = new ArrayList<>();

}
