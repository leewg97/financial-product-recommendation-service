package com.fastcampus.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @ApiModelProperty(value = "회원 이메일")
        private String email;
        @ApiModelProperty(value = "비밀번호")
        private String password;
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 지역")
        private String region;
        @ApiModelProperty(value = "지원금")
        private int hopeAmount;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        @ApiModelProperty(value = "회원 고유 번호")
        private Long id;
        @ApiModelProperty(value = "회원 고유 번호")
        private String email;
        @ApiModelProperty(value = "회원 이름")
        private String username;
        @ApiModelProperty(value = "회원 직업")
        private String job;
        @ApiModelProperty(value = "회원 지역")
        private String region;
        @ApiModelProperty(value = "지원금")
        private int hopeAmount;
    }

}
