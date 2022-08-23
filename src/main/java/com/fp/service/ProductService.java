package com.fp.service;

import com.fp.domain.Product;
import com.fp.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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
