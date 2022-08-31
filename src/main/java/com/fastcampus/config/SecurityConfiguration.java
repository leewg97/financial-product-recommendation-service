package com.fastcampus.config;

import com.fastcampus.Security.jwtFilter.JwtAuthorizationFilter;
import com.fastcampus.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;

    private final CorsFilter corsFilter;

    // 스웨거 관련 설정

    private static final String[] AUTH_ARR = {
            "/swagger/**",
            "/swagger-ui.html",
            "/swagger-resources/**"
    };

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico");

        // swagger
        web.ignoring().antMatchers(
                "/v3/api-docs",  "/configuration/ui",
                "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**","/swagger/**", "/v2/api-docs");
        web.ignoring()
                .antMatchers(AUTH_ARR);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable();
        // CSRF 토큰을 전달하지 않는다.
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.addFilter(corsFilter);
        // 세션 false
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Bearer 인증 방식을 사용하겠다.
        http.httpBasic().disable();
        // 폼로그인 false
//        http.formLogin().disable();
//        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository));
        // 다음 경로에 대한 요청은 인증 없이 접근을 허용하도록 설정한다.
        http.authorizeRequests().antMatchers("/", "/auth/**", "/home/**", "/js/**", "/image/**","/swagger-resources/**","/swagger-ui/**").permitAll();
        // 위에서 언급한 경로 외에는 모두 인증을 거치도록 설정한다.
        http.authorizeRequests().anyRequest().authenticated();
//        // 모든 경로 인증 없이 허용
//        http.authorizeRequests().anyRequest().permitAll();
        // 사용자가 만든 로그인 화면을 띄운다.
        http.formLogin().loginPage("/sigin-in");
        // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인 진행
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
//        http.formLogin().loginProcessingUrl("/auth/login");
        // 로그인 후 메인페이지로 이동
        http.formLogin().defaultSuccessUrl("/");
        // 로그아웃 path
        http.logout().logoutUrl("/member/logout").logoutSuccessUrl("/");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}