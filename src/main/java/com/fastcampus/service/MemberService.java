package com.fastcampus.service;

import com.fastcampus.domain.Bookmark;
import com.fastcampus.domain.Cart;
import com.fastcampus.domain.Member;
import com.fastcampus.persistence.BookmarkRepository;
import com.fastcampus.persistence.CartRepository;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    // 생성자 주입
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final BookmarkRepository bookmarkRepository;

    // 회원 등록
    @Transactional
    public Member insertUser(MemberDto.registerRequest req) {
        Member member = new Member();
        member.setEmail(req.getEmail());
        member.setPassword(passwordEncoder.encode(req.getPassword()));
        member.setUsername(req.getUsername());
        member.setJob("소상공인");
        member.setRegion(req.getRegion());
        member.setHopeAmount(req.getHopeAmount());

        memberRepository.save(member);

        // 회원가입과 동시에 장바구니 생성
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.addCart(member);
            cartRepository.save(cart);
        }

        // 회원가입과 동시에 북마크 생성
        Bookmark bookmark = bookmarkRepository.findByMemberId(member.getId());
        if (bookmark == null) {
            bookmark = Bookmark.addBookmark(member);
            bookmarkRepository.save(bookmark);
        }

        return member;
    }

    // 회원가입 전 db에 같은 이름이 존재하는지 검색
    @Transactional
    public Member getMember(String email) {
        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return findUser;
        }
        return new Member();
    }

    // MemberDto로 회원 검색
    @Transactional
    public MemberDto.Response resGetMember(String email) {
        Member findUser = memberRepository.findByEmail(email);
        if(findUser != null) {
            return new MemberDto.Response(
                    findUser.getId(),
                    findUser.getEmail(),
                    findUser.getUsername(),
                    findUser.getJob(),
                    findUser.getRegion(),
                    findUser.getHopeAmount()
            );
        }
        return null;
    }

    // 회원 업데이트
    public MemberDto.Response MemberUpdate(Member member, Member updateMember) {
        member.setUsername(updateMember.getUsername());
        member.setRegion(updateMember.getRegion());
        member.setHopeAmount(updateMember.getHopeAmount());
        Member result = memberRepository.save(member);
        return new MemberDto.Response(
                result.getId(),
                result.getEmail(),
                result.getUsername(),
                result.getJob(),
                result.getRegion(),
                result.getHopeAmount()
        );
    }

}