package com.fastcampus.persistence;

import com.fastcampus.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findByEmail(String email);

    Member findByEmail(String email);

}
