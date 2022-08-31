package com.fastcampus.web.controller;

import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.domain.Member;
import com.fastcampus.service.MemberService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.MemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"회원 관련 Controller"})
@Controller
@RequiredArgsConstructor
public class MemberController {

    // 생성자 주입
    private final MemberService memberService;

    //회원 정보 찾기
    @ApiOperation(value="회원 정보 찾기")
    @GetMapping("/member/information")
    public @ResponseBody ResponseEntity memberInformation(Authentication authentication) {
        if(authentication == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_FOUND_USER, null), HttpStatus.OK);
        }
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String email = principal.getMember().getEmail();
        MemberDto.Response resMember = memberService.resGetMember(email);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, resMember), HttpStatus.OK);
    }

    // 회원가입 처리
    @ApiOperation(value="회원 가입")
    @PostMapping("/auth/register")
    public @ResponseBody ResponseEntity insertUser(@RequestBody MemberDto.registerRequest req) {
        Member findMember = memberService.getMember(req.getEmail());

        if(findMember.getEmail() == null) {
            Member newMember = memberService.insertUser(req);
            MemberDto.Response resMember = memberService.resGetMember(newMember.getEmail());
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER, resMember), HttpStatus.OK);
        } else {
            return new ResponseEntity(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.CREATED_FAIL, findMember.getEmail() + "은 이미 존재하는 이메일입니다."), HttpStatus.OK);
        }
    }

    // 회원 업데이트
    @ApiOperation(value="회원 수정")
    @PutMapping("/member/update")
    public @ResponseBody ResponseEntity MemberUpdate(Authentication authentication, @RequestBody Member updateMember) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Member member = principal.getMember();
        MemberDto.Response newMember = memberService.MemberUpdate(member, updateMember);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER, newMember), HttpStatus.OK);
    }

}
