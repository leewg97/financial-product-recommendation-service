package com.fastcampus.service;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.CartRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 장바구니 등록
    @Transactional
    public Cart addCart(Long memberId, Long productId) {
        Cart cart = new Cart();

        Member member = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        cart.addCart(member, product);
        return cartRepository.save(cart);
    }

    // 장바구니 조회
    @Transactional(readOnly = true)
    public List<Cart> findCarts(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) {
            return cartRepository.findByMember(findMember.get());
        }
        throw new RuntimeException();
    }

    // 장바구니 삭제
    @Transactional
    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    // 장바구니 신청
//    @Transactional
//    public void askCart(Member member) {
//        Cart cart = (Cart) cartRepository.findAllByMember(member);
//        cartRepository.save(cart);
//    }
}
