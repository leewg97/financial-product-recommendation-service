package com.fastcampus.service;

import com.fastcampus.domain.Member;
import com.fastcampus.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    // 생성자 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 등록
    @Transactional
    public void insertUser(Member member) {
        member.setJob("소상공인");
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
    }

    // 회원 검색
    @Transactional
    public Member getMember(String email) {
        Optional<Member> findUser = memberRepository.findByEmail(email);
        if(findUser.isPresent()) {
            return findUser.get();
        }
        return new Member();
    }

}
