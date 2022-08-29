package com.fastcampus.service;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.CartProduct;
import com.fastcampus.domain.Member;
import com.fastcampus.persistence.CartRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.web.dto.MemberDto;
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
    public Member insertUser(Member member) {
        member.setJob("소상공인");
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);

        // 회원가입과 동시에 장바구니 생성
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.addCart(member);
            cartRepository.save(cart);
        }
        return member;
    }

    // 회원가입 전 db에 같은 이름이 존재하는지 검색
    @Transactional
    public Member getMember(String email) {
        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return findUser;
        }
        return new Member();
    }

    // MemberDto로 회원 검색
    @Transactional
    public MemberDto.Response resGetMember(String email) {
        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return new MemberDto.Response(
                    findUser.getId(),
                    findUser.getEmail(),
                    findUser.getUsername(),
                    findUser.getJob(),
                    findUser.getRegion(),
                    findUser.getHopeAmount()
            );
        }
        return null;
    }

    // 회원 업데이트
    public MemberDto.Response MemberUpdate(Member member, Member updateMember) {
        member.setUsername(updateMember.getUsername());
        member.setRegion(updateMember.getRegion());
        member.setHopeAmount(updateMember.getHopeAmount());
        Member result = memberRepository.save(member);
        MemberDto.Response resMember = new MemberDto.Response(
                result.getId(),
                result.getEmail(),
                result.getUsername(),
                result.getJob(),
                result.getRegion(),
                result.getHopeAmount()
        );
        return resMember;
    }
}
