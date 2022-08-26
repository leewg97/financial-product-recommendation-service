package com.fastcampus.web.dto;

import io.swagger.annotations.ApiModelProperty;
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
        @ApiModelProperty(value = "검색조건", example = "TITLE", required = true)
        @Enumerated(value = EnumType.STRING)
        private SearchCondition searchCondition;
        @ApiModelProperty(value = "검색키워드", example = "특별경영안정자금", required = false)
        private String searchKeyword;
    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        @ApiModelProperty(value = "상품이름", example = "특별경영안전자금")
        private Long id;
        @ApiModelProperty(value = "상품이름", example = "특별경영안전자금")
        private String productName; // 상품 이름
        @ApiModelProperty(value = "상품 내용", example = "경기침체, 재해피해 소상공인 지원")
        private String productContent; //
        @ApiModelProperty(value = "지원 은행", example = "정부")
        private String supporterName; //
        @ApiModelProperty(value = "지원 지역", example = "서울")
        private String supporterRegion; // 지원 지역
        @ApiModelProperty(value = "지원 금액", example = "3500000")
        private int supporterAmount; //
    }
}
