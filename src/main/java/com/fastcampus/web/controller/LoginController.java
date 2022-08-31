package com.fastcampus.web.controller;

import com.fastcampus.service.LoginService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = {"로그인 Controller"})
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

  private final LoginService loginService;

  // 로그인
  @ApiOperation(value="로그인")
  @PostMapping("/login")
  public @ResponseBody ResponseEntity login (@RequestBody LoginDto.Request req) {
    LoginDto.Response response = loginService.login(req);
    if(response != null) {
      return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, response), HttpStatus.OK);
    }
    return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_FAIL, null), HttpStatus.OK);
  }
}
