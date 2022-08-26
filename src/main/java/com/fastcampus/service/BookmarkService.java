package com.fastcampus.service;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 찜 등록
    @Transactional
    public Bookmark addBookmark(Long memberId, Long productId) {
        Bookmark bookmark = new Bookmark();

        Member member = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        bookmark.addBookmark(member, product);
        return bookmarkRepository.save(bookmark);
    }

    // 찜 조회
    @Transactional(readOnly = true)
    public List<Bookmark> findBookmarks(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) {
            return bookmarkRepository.findByMember(findMember.get());
        }
        throw new RuntimeException();
    }

    // 찜 삭제
    @Transactional
    public void deleteBookmark(Long bookmarkId) {
        bookmarkRepository.deleteById(bookmarkId);
    }

}
