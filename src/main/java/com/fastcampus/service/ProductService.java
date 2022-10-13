package com.fastcampus.service;

import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.BookmarkProduct;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.BookmarkProductRepository;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkProductRepository bookmarkProductRepository;

    // 찜여부
    public boolean isBookmark(Long memberId, Long productId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        // 회원에 해당하는 찜한 북마크
        Bookmark bookmark = bookmarkRepository.findByMember(member);
        // 해당 북마크에서의 북마크아이디와 같은것들.(상품)
        List<BookmarkProduct> bookmarkProducts = bookmarkProductRepository.findAllByBookmark(bookmark);
        // 해당 북마크에서의 북마크아이디와 같은것들.(상품)  과 프로덕트 아이디와 같은것들
        for (int bookmarkProductsIndex = 0; bookmarkProductsIndex <bookmarkProducts.size(); bookmarkProductsIndex++) {
            if(productId == bookmarkProducts.get(bookmarkProductsIndex).getProduct().getId()){
                return true;
            }else{
                continue;
            }
        }
        return false;
    }



    // productResponseDTO 값 설정
    public List<ProductDto.Response> getProductResList(List<Product> productList, Authentication authentication){
        List<ProductDto.Response> productDtoList = new ArrayList<>();
        // 토큰을 통하여 memberId를 얻어온다.
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        long memberId = principal.getMember().getId();
        // dto에 값 주입
        for (Product findProduct : productList) {
            ProductDto.Response productResDto = new ProductDto.Response(
                    findProduct.getId(),
                    findProduct.getProductName(),
                    findProduct.getProductContent(),
                    findProduct.getSupporterName(),
                    findProduct.getSupporterRegion(),
                    findProduct.getSupporterAmount(),
                    isBookmark(memberId, findProduct.getId())
            );
            productDtoList.add(productResDto);
        }
        return productDtoList;
    }

    // 상품 검색
    @Transactional
    public List<ProductDto.Response> SearchProducts(ProductDto.Request productDto,Authentication authentication) {
        if (productDto.getSearchCondition().equals(SearchCondition.TITLE)) {
            List<Product> productList = productRepository.findByProductNameContaining(productDto.getSearchKeyword());
            return getProductResList(productList,authentication);
        } else if (productDto.getSearchCondition().equals(SearchCondition.CONTENT)) {
            List<Product> productList = productRepository.findByProductContentContaining(productDto.getSearchKeyword());
            return getProductResList(productList,authentication);
        }
        List<Product> productList = productRepository.findAll();
        return getProductResList(productList,authentication);
    }

    // 맞춤상품
    @Transactional
    public List<ProductDto.Response> customProducts(Authentication authentication) throws Exception {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        long memberId = principal.getMember().getId();
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            Member findMember = member.get();
            int amount = findMember.getHopeAmount();
            String region = findMember.getRegion();
            List<Product> productList = productRepository.findBySupporterAmountGreaterThanEqualAndSupporterRegion(amount, region);
            return getProductResList(productList,authentication);
        }else{
            throw new Exception("회원이 존재하지 않습니다.");
        }
    }

    // 상품 조회
    @Transactional
    public ProductDto.Response getProduct(Long productId,Authentication authentication) throws Exception {
        Optional<Product> findProduct =  productRepository.findById(productId);
        // 토큰을 통하여 memberId를 얻어온다.
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        long memberId = principal.getMember().getId();
        if(findProduct.isPresent()){
            Product product = findProduct.get();
            return new ProductDto.Response(
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount(),
                    isBookmark(memberId,product.getId())
            );
        }else{
            throw new Exception("해당상품은 존재하지 않습니다.");
        }
    }

    // 상품 목록
    @Transactional
    public List<ProductDto.Response> productList(Authentication authentication) {
        List<Product> productList = productRepository.findAll();
        return getProductResList(productList,authentication);
    }

}