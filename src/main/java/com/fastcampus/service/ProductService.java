package com.fastcampus.service;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    private final BookmarkRepository bookmarkRepository;

    // 찜여부
    public boolean isBookmark(Long memberId, Long productId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        List<Bookmark> bookmark = bookmarkRepository.findByMember(member);
        for (int i = 0; i <bookmark.size() ; i++) {
            // 찜한 제품 아이디 찾고
            if(productId == bookmark.get(i).getProduct().getId()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    // productResponseDTO 값 설정
    public List<ProductDto.Response> getProductResList(List<Product> productList, Long memberId){
        List<ProductDto.Response> productDtoList = new ArrayList<>();
        for (int i = 0; i <productList.size() ; i++) {
            Product product = productList.get(i);
            ProductDto.Response productResDto =new ProductDto.Response(
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount(),
                    isBookmark(memberId,product.getId())
            );
            productDtoList.add(productResDto);
        }
        return productDtoList;
    }

    // 상품 검색
    @Transactional
    public List<ProductDto.Response> SearchProducts(ProductDto.Request productDto,Long memberId) {
        if (productDto.getSearchCondition().equals(SearchCondition.TITLE)) {
            List<Product> productList = productRepository.findByProductNameContaining(productDto.getSearchKeyword());
            return getProductResList(productList,memberId);
        } else if (productDto.getSearchCondition().equals(SearchCondition.CONTENT)) {
            List<Product> productList = productRepository.findByProductContentContaining(productDto.getSearchKeyword());
            return getProductResList(productList,memberId);
        }
        List<Product> productList = productRepository.findAll();
        return getProductResList(productList,memberId);
    }

    // 맞춤상품
    @Transactional
    public List<ProductDto.Response> customProducts(Long productId,Long memberId) throws Exception {
        Optional<Member> member = memberRepository.findById(productId);
        if (member.isPresent()) {
            Member findMember = member.get();
            int amount = findMember.getHopeAmount();
            String region = findMember.getRegion();
            List<Product> productList = productRepository.findBySupporterAmountGreaterThanEqualAndSupporterRegion(amount, region);
            log.info(productList.toString());
            return getProductResList(productList,memberId);
        }else{
            throw new Exception("회원이 존재하지 않습니다.");
        }
    }

    // 상품 조회
    @Transactional
    public ProductDto.Response getProduct(Long productId,Long memberId) throws Exception {
        Optional<Product> findProduct =  productRepository.findById(productId);
        if(findProduct.isPresent()){
            Product product = findProduct.get();
            ProductDto.Response productDto = new ProductDto.Response(
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount(),
                    isBookmark(memberId,product.getId())
            );
            return productDto;
        }else{
            throw new Exception("해당상품은 존재하지 않습니다.");
        }
    }

    // 상품 목록
    @Transactional
    public List<ProductDto.Response> productList(Long memberId) {
        List<Product> productList =productRepository.findAll();
        return getProductResList(productList,memberId);
    }

}