package com.fp.service;

import com.fp.domain.Cart;
import com.fp.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
