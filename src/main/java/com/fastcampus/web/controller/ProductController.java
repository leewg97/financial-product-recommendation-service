package com.fastcampus.web.controller;

import com.fastcampus.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api")
    public String customProduct(Long id){
        productService.customProduct(2L);
        return "good";
    }
}
