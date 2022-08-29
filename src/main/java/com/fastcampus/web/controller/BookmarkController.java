package com.fastcampus.web.controller;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.service.BookmarkService;
import com.fastcampus.web.api.DefaultRes;
import com.fastcampus.web.api.ResponseMessage;
import com.fastcampus.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // Bookmark 등록
    @PostMapping("/{productId}")
    public @ResponseBody ResponseEntity insertBookmark(@PathVariable Long productId,  Authentication authentication) throws Exception {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.ADD_BOOKMARK, bookmarkService.addBookmark(productId,authentication)), HttpStatus.OK);
    }

    // Bookmark 조회
    @GetMapping("/{memberId}")
    public @ResponseBody ResponseEntity bookmarkList(@PathVariable Long memberId) {
        List<Bookmark> findBookmark = bookmarkService.findBookmarks(memberId);

        if (!findBookmark.isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_BOOKMARK, bookmarkService.findBookmarks(memberId)), HttpStatus.OK);
        }
        return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NOT_FOUND_BOOKMARK, "찜 목록이 존재하지 않습니다."), HttpStatus.OK);
    }

    // Bookmark 삭제
    @DeleteMapping("/{bookmarkId}")
    @ResponseBody
    public ResponseEntity deleteBookmark(@PathVariable Long bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_BOOKMARK, "찜 목록이 삭제 되었습니다."), HttpStatus.OK);
    }

}
