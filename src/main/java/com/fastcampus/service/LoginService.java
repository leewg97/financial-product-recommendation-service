package com.fastcampus.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.Security.jwtFilter.JwtProperties;
import com.fastcampus.web.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    // 로그인
    @Transactional
    public LoginDto.Response login(LoginDto.loginRequest req) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword());

        Authentication authentication =
                authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        PrincipalDetails principalDetailis = (PrincipalDetails) authentication.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetailis.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ JwtProperties.EXPIRATION_TIME))
                .withClaim("id", principalDetailis.getMember().getId())
                .withClaim("email", principalDetailis.getMember().getEmail())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        LoginDto.Response response = new LoginDto.Response();
        response.setAuthorization(JwtProperties.TOKEN_PREFIX + jwtToken);
        response.setEmail(req.getEmail());

        return response;
    }

}