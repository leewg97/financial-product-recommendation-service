package com.fastcampus.service;

import com.fastcampus.domain.Member;
import com.fastcampus.domain.Product;
import com.fastcampus.persistence.MemberRepository;
import com.fastcampus.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 맞춤상품
    @Transactional
    public List<Product> customProduct(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Member findMember = member.get();
            int amount = findMember.getHopeAmount();
            String region = findMember.getRegion();

            List<Product> productList = productRepository.findBySupporterAmountGreaterThanEqualAndSupporterRegion(amount, region);
            System.out.println(productList);
            log.info(productList.toString());
            return productList;
        }
        return new ArrayList<>();
    }


//    // 상품 조회
//    @Transactional
//    public Product getProduct(long id) {
//        return productRepository.findById(id).get();
//    }
//
//    // 상품 목록
//    @Transactional
//    public Page<Product> getPostList(Pageable pageable) {
//        return productRepository.findAll(pageable);
//    }

}
