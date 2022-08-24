package com.fp.web.controller;

import com.fp.domain.Member;
import com.fp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

//    @Autowired
//    private MemberService memberService;
//
//    // 회원가입 처리
//    @PostMapping("/auth/insertUser")
//    public @ResponseBody String insertUser(@RequestBody Member member) {
//        // username으로 등록된 회원이 있나 검색
//        Member findUser = memberService.getUser(member.getUserName());
//
//        if(findUser.getUserName() == null) {
//            memberService.insertUser(member);
//            return member.getUsername() + " 회원 가입 성공";
//        } else {
//            return member.getUsername() + " 이미 존재하는 아이디입니다.";
//        }
//    }

}
