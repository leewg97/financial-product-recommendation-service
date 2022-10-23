# Financial-Product-Recommendation-Service

## 팀원
|이원근<br>(BE 팀장)|이창희|김대곤|고영민|
|:----:|:----:|:----:|:----:|
|[@leewg97](https://github.com/leewg97)|[@WindowH22](https://github.com/WindowH22)|[@bbyuggyu](https://github.com/bbyuggyu)|[@mini-inib](https://github.com/mini-inib)|
|서버구축, DB설계, API설계, API구현|서버구축, DB설계, API설계, API구현|서버구축, DB설계, API설계, API구현|X| 

<br>

## 개발 환경

- IDE : IntelliJ
- JDK : 11
- DB : MySQL
- Spring Boot : 2.7.3
- Build : Gradle

<br>

## 사용 기술

- Spring Web
- Spring MVC
- Spring Data JPA
- Spring DevTools
- Spring Security
- Swagger
- JWT
- EC2
- RDS
- Jenkins
- Docker

<br>

## ERD

![ERD.png](src/main/resources/static/ERD.png)

<br>

## API 명세서

[API 명세서 바로가기](https://www.notion.so/996655c9789b4fdfb5948e16f34628d6)

### 회원 관련
  - `POST`  
    - `/auth/register` : 회원 가입
    - `/auth/login` : 로그인
  - `GET`
    - `/member/information` : 회원 정보 찾기
  - `PUT`
    - `/member/update` : 회원 수정

### 상품 관련
  - `GET`
    - `/products/list` : 상품 목록
    - `/products/recommend` : 맞춤 상품
    - `/products/{productId}` : 상품 조회
    - `/products/search` : 상품 검색

### 장바구니
  - `POST`
    - `/carts/add` : 장바구니 등록
  - `GET`
    - `/carts/find` : 장바구니 목록 조회
  - `DELETE`
    - `/carts/{id}` : 장바구니 삭제
    - `/carts/order` : 장바구니 신청(신청하면 장바구니 비워지게만 구현)

### 찜
  - `POST`
    - `/bookmarks/add` : 찜 등록
  - `GET`
    - `/bookmarks/find` : 찜 목록 조회
  - `DELETE`
    - `/bookmarks/{productId}` : 찜 삭제


