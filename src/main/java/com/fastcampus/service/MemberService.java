package com.fastcampus.service;

import com.fastcampus.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

//    // 회원 등록
//    @Transactional
//    public void insertUser(Member member) {
//        member.setJob("소상공인");
//        memberRepository.save(member);
//    }

//    // 회원 검색
//    @Transactional
//    public Member getMember(long id) {
//        Optional<Member> findUser = memberRepository.findById(id));
//        if(findUser.isPresent()) {
//            return findUser.get();
//        }
//        return new Member();
//    }

}
