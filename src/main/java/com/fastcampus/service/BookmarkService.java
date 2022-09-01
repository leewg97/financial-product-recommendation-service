package com.fastcampus.service;

import com.fastcampus.domain.*;
import com.fastcampus.persistence.BookmarkProductRepository;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.BookmarkProductDto;
import com.fastcampus.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkProductRepository bookmarkProductRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final ProductService productService;


    // 찜 등록
    @Transactional
    public ProductDto.Response addBookmark(BookmarkProductDto.Request request, Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("해당 상품이 존재하지 않습니다."));

        Bookmark bookmark = bookmarkRepository.findByMemberId(member.getId());
        BookmarkProduct bookmarkProduct = request.toEntity(bookmark, product);

        // 상품이 찜 목록에 들어가있는지 확인
        BookmarkProduct findBookmarkProduct =
                bookmarkProductRepository.findByBookmarkIdAndProductId(bookmark.getId(), product.getId());
        if (findBookmarkProduct != null) {
            throw new RuntimeException("해당 상품은 이미 찜 목록에 등록되어 있습니다.");
        }
        BookmarkProduct.addBookmarkProduct(bookmark, product);
        bookmarkProductRepository.save(bookmarkProduct);

        ProductDto.Response productDto = new ProductDto.Response(
                product.getId(),
                product.getProductName(),
                product.getProductContent(),
                product.getSupporterName(),
                product.getSupporterRegion(),
                product.getSupporterAmount(),
                productService.isBookmark(member.getId(),product.getId())
        );
        return productDto;
    }


    // 찜 목록 리스트 조회
    @Transactional(readOnly = true)
    public List<ProductDto.bookmarkResponse> findBookmarks(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        List<BookmarkProduct> findBookmarkProducts = bookmarkProductRepository.findAllByBookmark(findMember.getBookmark());
        List<ProductDto.bookmarkResponse> bookmarkList = new ArrayList<>();

        for (int i = 0; i <findBookmarkProducts.size() ; i++) {

            Product product = findBookmarkProducts.get(i).getProduct();
            ProductDto.bookmarkResponse productResDto =new ProductDto.bookmarkResponse(
                    findBookmarkProducts.get(i).getId(),
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount()
            );
            bookmarkList.add(productResDto);
        }
        return bookmarkList;
    }

    // 찜 삭제 (해당하는 제품 하나)
    @Transactional
    public ProductDto.Response delete(Long memberId, Long productId) {

        Product product = productRepository.findById(productId).orElseThrow();
        BookmarkProduct bookmarkProduct = bookmarkProductRepository.findByBookmarkIdAndProductId(memberId, product.getId());

        bookmarkProductRepository.delete(bookmarkProduct);

        ProductDto.Response productDto = new ProductDto.Response(
                product.getId(),
                product.getProductName(),
                product.getProductContent(),
                product.getSupporterName(),
                product.getSupporterRegion(),
                product.getSupporterAmount(),
                productService.isBookmark(memberId, product.getId())
        );
        return productDto;
    }

}
