package com.fastcampus.web.controller;

import com.fastcampus.domain.Cart;
import com.fastcampus.service.CartService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;


    // 장바구니 등록
//    @PostMapping("/{id}/cart")
//    public @ResponseBody ResponseEntity addCart(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ADD_CART, cartService.addCart(principalDetails.getMember().getId(), id);), HttpStatus.OK);
//    }

    // 장바구니 등록
    @PostMapping("/{memberId}/{productId}")
    public @ResponseBody ResponseEntity addCart(@PathVariable Long productId, @PathVariable Long memberId) {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ADD_CART, cartService.addCart(memberId, productId)), HttpStatus.OK);
    }

    // 장바구니 조회
//    @GetMapping("/cart")
//    public @ResponseBody ResponseEntity getCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_CART, cartService.findCarts(principalDetails.getMember())), HttpStatus.OK);
//    }

    // 장바구니 조회
    @GetMapping("/{memberId}")
    public @ResponseBody ResponseEntity getCart(@PathVariable Long memberId) {
        List<Cart> findCart = cartService.findCarts(memberId);

        if (!findCart.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_CART, cartService.findCarts(memberId)), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_FOUNT_CART, "장바구니 목록이 존재하지 않습니다."), HttpStatus.OK);
    }

    // 장바구니 삭제
    @DeleteMapping("/{cartId}")
    public @ResponseBody ResponseEntity deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_CART, "장바구니 목록이 삭제 되었습니다."), HttpStatus.OK);
    }

    // 장바구니 요청
//    @PostMapping("/ask")
//    public @ResponseBody ResponseEntity askCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        cartService.askCart(principalDetails.getMember());
//        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ASK_CART), HttpStatus.OK);
//    }
}
