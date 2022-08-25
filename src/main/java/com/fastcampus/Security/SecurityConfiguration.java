package com.fastcampus.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.headers().frameOptions().disable();
//        // 다음 경로에 대한 요청은 인증 없이 접근을 허용하도록 설정한다.
//        http.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/image/**").permitAll();
//        // 위에서 언급한 경로 외에는 모두 인증을 거치도록 설정한다.
//        http.authorizeRequests().anyRequest().authenticated();
        // 모든 경로 인증 없이 허용
        http.authorizeRequests().anyRequest().permitAll();
        // CSRF 토큰을 전달하지 않는다.
        http.csrf().disable();
        // 사용자가 만든 로그인 화면을 띄운다.
//        http.formLogin().loginPage("/loginForm");
        // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
        http.formLogin().usernameParameter("email");
        http.formLogin().loginProcessingUrl("/login");
        // 로그인 후 메인페이지로 이동
        http.formLogin().defaultSuccessUrl("/");
        // 로그아웃 path
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}