package com.fastcampus.web.controller;

import com.fastcampus.domain.Product;
import com.fastcampus.service.ProductService;
import com.fastcampus.web.dto.ProductDto;
import com.fastcampus.web.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;

    @GetMapping
    public String index() {
        return "/";
    }

    // 상품 검색
    @GetMapping("/search")
    public String searchProduct(ProductDto productDto, Model model) {
        if (Objects.equals(productDto.getSearchCondition(), SearchCondition.TITLE)) productDto.setSearchCondition(SearchCondition.TITLE);
        if (productDto.getSearchKeyword() == null) productDto.setSearchKeyword("");

        model.addAttribute("condition", productDto.getSearchCondition());
        model.addAttribute("keyword", productDto.getSearchKeyword());

        List<Product> searchResult = productService.SearchProducts(productDto);

        model.addAttribute("searchResult", searchResult);
        return "redirect:/";
    }

}
