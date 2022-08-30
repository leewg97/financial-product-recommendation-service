package com.fastcampus.persistence;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findById(Long id);

    Bookmark findByMemberId(Long memberId);

    Bookmark findByMember(Member member);
}
