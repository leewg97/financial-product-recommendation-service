package com.fastcampus.service;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.CartProduct;
import com.fastcampus.domain.Member;
import com.fastcampus.persistence.CartRepository;
import com.fastcampus.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    // 생성자 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;

    // 회원 등록
    @Transactional
    public void insertUser(Member member) {
        member.setJob("소상공인");
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);

        // 회원가입과 동시에 장바구니 생성
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) { 
            cart = Cart.addCart(member);
            cartRepository.save(cart);
        }

    }

    // 회원 검색
    @Transactional
    public Member getMember(String email) {
        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return findUser;
        }
        return new Member();
    }

}
