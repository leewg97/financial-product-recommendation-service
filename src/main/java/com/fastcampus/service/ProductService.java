package com.fastcampus.service;

import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
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

    // 상품 검색
    @Transactional
    public List<Product> SearchProducts(ProductDto productDto) {
        if (productDto.getSearchCondition().equals(SearchCondition.TITLE)) {
            return productRepository.findByProductNameContaining(productDto.getSearchKeyword());
        } else if (productDto.getSearchCondition().equals(SearchCondition.CONTENT)) {
            return productRepository.findByProductContentContaining(productDto.getSearchKeyword());
        }
        return productRepository.findAll();
    }

    // 맞춤상품
    @Transactional
    public List<Product> customProducts(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member findMember = member.get();
            int amount = findMember.getHopeAmount();
            String region = findMember.getRegion();

            List<Product> productList = productRepository.findBySupporterAmountGreaterThanEqualAndSupporterRegion(amount, region);
            log.info(productList.toString());
            return productList;
        }
        return new ArrayList<>();
    }

    // 상품 조회
    @Transactional
    public Product getProduct(long id) {
        return productRepository.findById(id).get();
    }

    // 상품 목록
    @Transactional
    public List<Product> productList() {
        return productRepository.findAll();
    }

}
