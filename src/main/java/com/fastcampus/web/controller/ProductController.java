package com.fastcampus.web.controller;

import com.fastcampus.service.ProductService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



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
    @GetMapping("/recommend/{id}")
    public @ResponseBody ResponseEntity customProduct(@PathVariable Long id) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_CUSTOM_PRODUCT_LIST,productService.customProducts(id)), HttpStatus.OK);
    }


    // 상품 검색
    @GetMapping("/search")
    public @ResponseBody ResponseEntity searchProduct(@RequestParam String query,ProductDto.Request productDto)  {
        productDto.setSearchCondition(SearchCondition.TITLE);
        productDto.setSearchKeyword(query);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT_SEARCH,productService.SearchProducts(productDto)), HttpStatus.OK);
    }

    // 상품 조회
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity getProduct(@PathVariable Long id) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.GET_PRODUCT,productService.getProduct(id)), HttpStatus.OK);
    }

}
