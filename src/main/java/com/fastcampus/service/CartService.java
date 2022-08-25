package com.fastcampus.service;

import com.fastcampus.persistence.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

//    // 장바구니 목록
//    @Transactional
//    public Page<Cart> getPostList(Pageable pageable) {
//        return cartRepository.findAll(pageable);
//    }

}
