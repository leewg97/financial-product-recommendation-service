package com.fastcampus.persistence;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.BookmarkProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkProductRepository extends JpaRepository<BookmarkProduct, Long> {

    BookmarkProduct findByBookmarkIdAndProductId(Long bookmarkId, Long productId);

    List<BookmarkProduct> findAllByBookmark(Bookmark bookmark);

}