package com.fastcampus.Security.auth;


import com.fastcampus.domain.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// 시큐리티가 /login 주소 요청을 낚아 채서 로그인을 진행시킨다.
// 로그인 진행이 완료가 되면 시큐리티 session을 만들어 준다.
// Security Session => Authentication => UserDetails(PrincipalDetails)

@Data
public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 해당 유저의 권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사이트에서 일정 기간 동안 로그인을 안 하면 휴먼 계정으로 바꿀 때 사용.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
