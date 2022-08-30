package com.fastcampus.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKMARK_PRODCUT")
@Entity
@Builder
public class BookmarkProduct {

    @Id
    @Column(name = "bookmark_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookmark_id")
    private Bookmark bookmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public static BookmarkProduct addBookmarkProduct(Bookmark bookmark, Product product) {
        BookmarkProduct bookmarkProduct = new BookmarkProduct();
        bookmarkProduct.setBookmark(bookmark);
        bookmarkProduct.setProduct(product);
        return bookmarkProduct;
    }

}
