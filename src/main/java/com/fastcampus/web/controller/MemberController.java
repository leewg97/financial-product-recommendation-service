package com.fastcampus.web.controller;

import com.fastcampus.domain.Member;
import com.fastcampus.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 처리
    @PostMapping("/register")
    public @ResponseBody String insertUser(@RequestBody Member member) {
        Member findMember = memberService.getMember(member.getEmail());

        if(findMember.getEmail() == null) {
            memberService.insertUser(member);
            System.out.println(member);
            return member.getEmail() + " 회원 가입 성공";
        } else {
            return member.getEmail() + " 이미 존재하는 이메일입니다.";
        }
    }
    
}
