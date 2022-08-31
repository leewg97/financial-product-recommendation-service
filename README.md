# Financial-Product-Recommendation-Service

## **팀원**
- 이원근
- 이창희
- 김대곤
- 고영민
---
## **개발 환경**

- IDE : IntelliJ
- JDK : 17
- DB : H2 1.4.199
- Spring Boot : 2.7.3
- Build : Maven
---
## **사용 기술**

- Spring Web
- Spring MVC
- Spring Data JPA
- Spring DevTools
- Spring Security
- Swagger
- Lombok
- JWT
---
## **Git 전략**

- clone 후 개별 브랜치 작업
- commit - push 기능 구현 시 틈틈이
---
## **ERD**

![ERD.png](src/main/resources/static/ERD.png)

---
## **구현 목록**

- Member
    - 회원 가입
    - 로그인
    - 로그 아웃
    - 회원 수정
    - 회원 조회
- Product
    - 상품 목록
    - 맞춤 상품
    - 상품 조회
    - 상품 검색
      - "TITLE"과 "CONTENT"로 검색
- Cart
    - 장바구니 등록
    - 장바구니 목록 조회
    - 장바구니 삭제
    - 장바구니 신청
      - 신청하면 장바구니 비워지게만 구현
- Bookmark
    - 찜 등록
    - 찜 목록 조회
    - 찜 삭제
---
## **규칙**

- Member와 Cart는 1 : 1 관계 → 하나의 Cart를 갖고 그 카트 내에 여러개의 Product
- Member와 Bookmark는 1 : 1 관계 → 하나의 Bookmark를 갖고 그 카트 내에 여러개의 Product
- 그러므로 Cart와 Product는 1 : N 관계
- 기본적으로 `(fetch = FetchType.*LAZY)*`
- 후에 시간정보 필요하면 Listener로 관리
- Entity에서 setter대신 생성자 주입을 사용하는건 어떨까? - 쓰는게 좋다
- dto사용(Inner class)
- 컬렉션 `NullPointerException`미연에 방지하기 위해 기본 리스트 값 생성
- `@Transactional`은 조회를 제외하고 전부 붙여주고 조회는 `readOnly = true`
    - update 에서 `save()`사용하지 말 것
- 의존성 주입 → `@RequiredArgsConstructor`와 함께 생성자 주입 방식 사용
---
## **API 명세서**

[API 명세서 바로가기](https://www.notion.so/996655c9789b4fdfb5948e16f34628d6)