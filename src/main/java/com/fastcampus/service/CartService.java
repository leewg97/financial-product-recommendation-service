package com.fastcampus.service;

import com.fastcampus.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

//    // 장바구니 목록
//    @Transactional
//    public Page<Cart> getPostList(Pageable pageable) {
//        return cartRepository.findAll(pageable);
//    }

}
