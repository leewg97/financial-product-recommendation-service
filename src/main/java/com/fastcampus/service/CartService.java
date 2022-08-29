package com.fastcampus.service;

import com.fastcampus.domain.Cart;
import com.fastcampus.domain.CartProduct;
import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.CartProductRepository;
import com.fastcampus.persistence.CartRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import com.fastcampus.web.dto.CartProductDto;
import com.fastcampus.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartProductRepository cartProductRepository;

    // 장바구니 등록
    public ProductDto.Response addCart(CartProductDto.Request request, Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new EntityNotFoundException("해당 상품이 존재하지 않습니다."));

        Cart cart = cartRepository.findByMemberId(member.getId());
        CartProduct cartProduct = request.toEntity(cart, product);

        // 상품이 장바구니에 들어가있는지 확인
        CartProduct findCartProduct = cartProductRepository.findByCartIdAndProductId(cart.getId(), product.getId());
        if (findCartProduct != null) {
            throw new RuntimeException("해당 상품은 이미 장바구니에 등록되어 있습니다.");
        }
        CartProduct.addCartProduct(cart, product);
        cartProductRepository.save(cartProduct);

        return ProductDto.res(product);
    }


    // 장바구니 리스트 조회
    @Transactional(readOnly = true)
    public List<ProductDto.CartResponse> findCarts(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));
        List<CartProduct> findCartProducts = cartProductRepository.findAllByCart(findMember.getCart());
        List<ProductDto.CartResponse> productList = new ArrayList<>();

        for (int i = 0; i <findCartProducts.size() ; i++) {
            Product product = findCartProducts.get(i).getProduct();
            ProductDto.CartResponse productResDto =new ProductDto.CartResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getProductContent(),
                    product.getSupporterName(),
                    product.getSupporterRegion(),
                    product.getSupporterAmount()
            );
            productList.add(productResDto);
        }
        return productList;
    }

    // 장바구니 삭제
    @Transactional
    public void deleteCartProduct(Long id) {
        cartProductRepository.deleteById(id);
    }

    // 장바구니 신청
    @Transactional
    public void deleteAll(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 존재하지 않습니다."));
        cartProductRepository.deleteAllInBatch(member.getCart().getCartProducts());
    }

}
