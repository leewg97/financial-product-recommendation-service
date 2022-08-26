package com.fastcampus.persistence;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findByMember(Member member);
}
