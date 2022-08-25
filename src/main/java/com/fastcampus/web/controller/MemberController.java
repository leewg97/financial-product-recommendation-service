package com.fastcampus.web.controller;

import com.fastcampus.domain.Member;
import com.fastcampus.service.MemberService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    // 회원가입 처리
    @PostMapping("/register")
    public @ResponseBody ResponseEntity insertUser(@RequestBody Member member) {
        Member findMember = memberService.getMember(member.getEmail());

        if(findMember.getEmail() == null) {
            memberService.insertUser(member);
            System.out.println(member);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, member), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.CREATED_FAIL, member.getEmail() + "은 이미 존재하는 이메일입니다."), HttpStatus.OK);
        }
    }

}
