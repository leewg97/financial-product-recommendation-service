package com.fastcampus.web.controller;

import com.fastcampus.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;

    @GetMapping
    public String index() {
        return "/";
    }

//    // 상품 검색
//    @GetMapping("/search")
//    public String searchProduct(ProductDto productDto, Model model) {
//        if (Objects.equals(productDto.getSearchCondition(), SearchCondition.TITLE)) productDto.setSearchCondition(SearchCondition.TITLE);
//        if (productDto.getSearchKeyword() == null) productDto.setSearchKeyword("");
//
//        model.addAttribute("condition", productDto.getSearchCondition());
//        model.addAttribute("keyword", productDto.getSearchKeyword());
//
//        List<Product> searchResult = productService.SearchProducts(productDto);
//
//        model.addAttribute("searchResult", searchResult);
//        return "redirect:/";
//    }

}
