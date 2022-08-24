package com.fp.web.controller;

import com.fp.domain.Member;
import com.fp.service.MemberService;
import com.fp.web.api.DefaultRes;
import com.fp.web.api.ResponseMessage;
import com.fp.web.api.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 처리
    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Member> insertUser(@RequestBody Member member) {
        Member findMember = memberService.getMember(member.getEmail());

        if(findMember.getEmail() == null) {
            memberService.insertUser(member);
            System.out.println(member);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, member), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.CREATED_FAIL, member.getEmail() + "은 이미 사용중인 이메일입니다."), HttpStatus.OK);
        }
    }

}
