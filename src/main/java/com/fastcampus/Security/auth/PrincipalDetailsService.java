package com.fastcampus.Security.auth;

import com.fastcampus.domain.Member;
import com.fastcampus.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시류리티 설정에서 loginProcessingUrl("/login")
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 돼어 있는 loadUserByUsername 함수가 실행

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return new PrincipalDetails(findUser);
        }
        return null;
    }

}
