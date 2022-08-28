package com.fastcampus.web.controller;

import com.fastcampus.service.ProductService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"상품 정보를 제공하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 목록
    @GetMapping("/list")
    public @ResponseBody ResponseEntity productList(Authentication authentication){
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_LIST,productService.productList(authentication)), HttpStatus.OK);

    }

    // 상품 추천
    @GetMapping("/recommend/{productId}")
    public @ResponseBody ResponseEntity customProduct(@ApiParam(value = "회원의아이디") @PathVariable Long productId,Authentication authentication) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST,productService.customProducts(productId,authentication)), HttpStatus.OK);
    }


    // 상품 검색
    @GetMapping("/search")
    public @ResponseBody ResponseEntity searchProduct(ProductDto.Request productDto,Authentication authentication)  {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH,productService.SearchProducts(productDto,authentication)), HttpStatus.OK);
    }

    // 상품 조회
    @GetMapping("/{productId}")
    public @ResponseBody ResponseEntity getProduct(@ApiParam(value = "상품의 index") @PathVariable Long productId,Authentication authentication) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT,productService.getProduct(productId,authentication)), HttpStatus.OK);
    }

}
