package com.fastcampus.web.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ProductDto {

    // 검색 관련 변수
    @Enumerated(value = EnumType.STRING)
    private SearchCondition searchCondition;

    private String searchKeyword;
    
}
