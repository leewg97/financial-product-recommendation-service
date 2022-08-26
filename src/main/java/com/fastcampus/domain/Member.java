package com.fastcampus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email; // 아이디

    private String password; // 비밀번호

    private String username; // 이름

    private String job; // 직업(소상공인)

    private String region; //지역

    private int hopeAmount; // 희망하는 지원 금액

}
