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
    public List<ProductDto.Response> SearchProducts(ProductDto.Request productDto) {
        if (productDto.getSearchCondition().equals(SearchCondition.TITLE)) {
            List<Product> productList = productRepository.findByProductNameContaining(productDto.getSearchKeyword());
            return getProductResList(productList);
        } else if (productDto.getSearchCondition().equals(SearchCondition.CONTENT)) {
            List<Product> productList = productRepository.findByProductContentContaining(productDto.getSearchKeyword());
            return getProductResList(productList);
        }
        List<Product> productList = productRepository.findAll();
        return getProductResList(productList);
    }
    // productResponseDTO 값 설정
    public static List<ProductDto.Response> getProductResList(List<Product> productList){
        List<ProductDto.Response> productDtoList = new ArrayList<>();
        for (int i = 0; i <productList.size() ; i++) {
            Product product = productList.get(i);
            ProductDto.Response productResDto =new ProductDto.Response(
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount()
            );
            productDtoList.add(productResDto);
        }
        return productDtoList;
    }

    // 맞춤상품
    @Transactional
    public List<Product> customProducts(Long id) throws Exception {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member findMember = member.get();
            int amount = findMember.getHopeAmount();
            String region = findMember.getRegion();

            List<Product> productList = productRepository.findBySupporterAmountGreaterThanEqualAndSupporterRegion(amount, region);
            log.info(productList.toString());
            return productList;
        }else{
            throw new Exception("회원이 존재하지 않습니다.");
        }
    }

    // 상품 조회
    @Transactional
    public Product getProduct(Long id) throws Exception {
        Optional<Product> findProduct =  productRepository.findById(id);
        if(findProduct.isPresent()){
            return findProduct.get();
        }else{
            throw new Exception("해당상품은 존재하지 않습니다.");
        }

    }

    // 상품 목록
    @Transactional
    public List<Product> productList() {
        return productRepository.findAll();
    }

}
