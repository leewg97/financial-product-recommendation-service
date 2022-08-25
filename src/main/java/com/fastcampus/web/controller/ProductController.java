package com.fastcampus.web.controller;

import com.fastcampus.service.ProductService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 상품 목록
    @GetMapping("/list")
    public @ResponseBody ResponseEntity productList(){
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_LIST,productService.productList()), HttpStatus.OK);

    }

    // 상품 추천
    @GetMapping("/recommend")
    public String customProduct(Long id){
        productService.customProduct(2L);
        return "good";
    }


    // 상품 검색


    // 상품 조회
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity getProduct(@PathVariable long id) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT,productService.getProduct(id)), HttpStatus.OK);
    }

}
