package com.fastcampus.web.controller;

import com.fastcampus.Security.auth.PrincipalDetails;
import com.fastcampus.service.BookmarkService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import com.fastcampus.web.dto.BookmarkProductDto;
import com.fastcampus.web.dto.ProductDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"찜 목록 정보를 제공하는 Controller"})
@Controller
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // 찜 등록
    @ApiOperation(value="찜 등록")
    @PostMapping("/add")
    public @ResponseBody ResponseEntity addBookmark(@RequestBody BookmarkProductDto.Request cartProductDto, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principal.getMember().getId();
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ADD_BOOKMARK, bookmarkService.addBookmark(cartProductDto, memberId)), HttpStatus.OK);
    }

    // 찜 목록 조회
    @ApiOperation(value="찜 목록 조회")
    @GetMapping("/find")
    public @ResponseBody ResponseEntity getCart(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principal.getMember().getId();

        List<ProductDto.bookmarkResponse> findCart = bookmarkService.findBookmarks(memberId);

        if (!findCart.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOOKMARK, bookmarkService.findBookmarks(memberId)), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_FOUND_BOOKMARK, "찜 목록이 존재하지 않습니다."), HttpStatus.OK);
    }

    // 찜 삭제
    @ApiOperation(value="찜 삭제")
    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity deleteCart(@PathVariable Long id) {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOOKMARK, bookmarkService.deleteBookmark(id)), HttpStatus.OK);
    }

}
