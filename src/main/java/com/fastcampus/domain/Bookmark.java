package com.fastcampus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bookmark {

    @Id
    @Column(name = "bookmark_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK
    private Member member;

    @OneToMany(mappedBy = "bookmark", cascade = CascadeType.REMOVE)
    private List<BookmarkProduct> bookmarkProducts = new ArrayList<>();

    public static Bookmark addBookmark(Member member) {
        Bookmark bookmark = new Bookmark();
        bookmark.setMember(member);
        return bookmark;
    }

}
