package com.fastcampus.web.controller;

import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.service.CartService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.CartProductDto;
import com.fastcampus.web.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"장바구니 정보를 제공하는 Controller"})
@Controller
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 장바구니 등록
    @ApiOperation(value="장바구니 등록")
    @PostMapping("/add")
    public @ResponseBody ResponseEntity addCart(
            @RequestBody CartProductDto.Request cartProductDto, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principal.getMember().getId();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ADD_CART,
                cartService.addCart(cartProductDto, memberId)), HttpStatus.OK);
    }

    // 장바구니 조회
    @ApiOperation(value="장바구니 조회")
    @GetMapping("/find")
    public @ResponseBody ResponseEntity getCart(Authentication authentication) throws Exception {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principal.getMember().getId();

        List<ProductDto.CartResponse> findCart = cartService.findCarts(memberId);

        if (!findCart.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_CART,
                    cartService.findCarts(memberId)), HttpStatus.OK);
        }
       return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_FOUNT_CART,
               "장바구니 목록이 존재하지 않습니다."), HttpStatus.OK);
    }

    // 장바구니 삭제
    @ApiOperation(value="장바구니 삭제")
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity deleteCart(@PathVariable Long id) {
        cartService.deleteCartProduct(id);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_CART,
                "장바구니 목록이 삭제 되었습니다."), HttpStatus.OK);
    }

    // 장바구니 신청
    @ApiOperation(value="장바구니 신청")
    @DeleteMapping("/order")
    public @ResponseBody ResponseEntity deleteAll(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principal.getMember().getId();
        cartService.deleteAll(memberId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ORDER_CART,
                "장바구니 신청이 완료되었습니다."), HttpStatus.OK);
    }

}
