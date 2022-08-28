package com.fastcampus.service;

import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    private final ProductService productService;

    // 찜 등록
    @Transactional
    public Bookmark addBookmark( Long productId, Authentication authentication) throws Exception {

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        long memberId = principal.getMember().getId();

        Member member = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        Bookmark bookmark = new Bookmark();
        bookmark.addBookmark(member, product);
        // 등록후 해당 제품 북마크 값이 true로 변경해줘야함
        productService.getProduct(productId,authentication);

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
