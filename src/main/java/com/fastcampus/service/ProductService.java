package com.fastcampus.service;

import com.fastcampus.domain.Product;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 검색
    @Transactional
    public List<Product> productList(ProductDto productDto) {
        if (productDto.getSearchCondition().equals(SearchCondition.TITLE)) {
            return productRepository.findByProductNameContaining(productDto.getSearchKeyword());
        } else if (productDto.getSearchCondition().equals(SearchCondition.CONTENT)) {
            return productRepository.findByProductContentContaining(productDto.getSearchKeyword());
        }
        return productRepository.findAll();
    }

//    // 상품 조회
//    @Transactional
//    public Product getProduct(long id) {
//        return productRepository.findById(id).get();
//    }
//
//    // 상품 목록
//    @Transactional
//    public Page<Product> getPostList(Pageable pageable) {
//        return productRepository.findAll(pageable);
//    }

}
