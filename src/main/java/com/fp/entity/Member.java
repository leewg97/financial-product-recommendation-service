package com.fp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String 아이디;

    private String 비밀번호;

    private int 나이;

    private String 취미;

    private String 직업;

    @OneToMany
    @JoinColumn(name = "member_id")
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
