package com.fastcampus.persistence;

import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    @Transactional
    void crudTest() {
        for (int i = 1; i < 4; i++) {
            Member member = new Member();
            member.setEmail("email" + i);
            member.setJob("학생");
            member.setHopeAmount(1000000);
            member.setPassword("password"  + i);
            member.setUsername("user" + i);
            member.setRegion("서울");
            memberRepository.save(member);
        }

        for (int i = 1; i < 4; i++) {
            Product product = new Product();
            product.setProductName("지원금"  + i);
            product.setSupporterRegion("서울");
            product.setSupporterName("소상공인 지원금"  + i);
            product.setProductContent("소상공인을 위한 정부의 지원금입니다.");
            product.setSupporterAmount(10000000);
            productRepository.save(product);
        }

        productRepository.findAll().forEach(System.out::println);
        memberRepository.findAll().forEach(System.out::println);
    }

}
