package com.fastcampus.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class ProductDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        // 검색 관련 변수
        @Enumerated(value = EnumType.STRING)
        private SearchCondition searchCondition;
        private String searchKeyword;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        private String productName; // 상품 이름
        private String productContent; // 상품 내용
        private String supporterName; // 지원 은행
        private String supporterRegion; // 지원 지역
        private int supporterAmount; // 지원 금액
    }
}
