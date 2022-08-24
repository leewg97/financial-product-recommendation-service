package com.fp.persistence;

import com.fp.domain.Member;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @Test
    void crudTest() {
        Member member = new Member();
        member.set아이디("user1");
        member.set비밀번호("1111");
        member.set나이(30);
        member.set직업("무직");
        member.set취미("코딩");
    }
}
