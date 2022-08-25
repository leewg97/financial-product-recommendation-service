package com.fastcampus.service;

import com.fastcampus.domain.Member;
import com.fastcampus.persistence.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MemberService {

    // 생성자 주입
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 등록
    @Transactional
    public void insertUser(Member member) {
        member.setJob("소상공인");
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
