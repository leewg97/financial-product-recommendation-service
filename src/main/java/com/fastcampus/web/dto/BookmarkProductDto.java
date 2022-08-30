package com.fastcampus.web.dto;

import com.fastcampus.domain.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Data
public class BookmarkProductDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long productId;

        public BookmarkProduct toEntity(Bookmark bookmark, Product product) {
            return BookmarkProduct.builder()
                    .bookmark(bookmark)
                    .product(product)
                    .build();
        }

    }

    @Getter
    @AllArgsConstructor
    public static class Response {
        
        @ApiModelProperty(value = "찜 아이디")
        private Long bookmarkId;
        @ApiModelProperty(value = "멤버 아이디")
        private Long memberId;
        @ApiModelProperty(value = "상품 아이디")
        private Long productId;

    }
    
    
}
