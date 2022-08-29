package com.fastcampus.web.api;

public class ResponseMessage {
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String CREATED_FAIL = "회원 가입 실패";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String GET_PRODUCT_LIST = "상품목록 조회 성공";
    public static final String GET_PRODUCT = "상품조회 성공";
    public static final String GET_CUSTOM_PRODUCT_LIST = "맞춤상품 조회 성공";
    public static final String GET_PRODUCT_SEARCH = "상품검색 성공";

    public static final String ADD_CART = "장바구니 등록 성공";
    public static final String READ_CART = "장바구니 목록 조회 성공";
    public static final String EXIST_CART = "장바구니에 이미 존재";
    public static final String NOT_FOUNT_CART = "장바구니 목록 없음";
    public static final String DELETE_CART = "장바구니 삭제 성공";
    public static final String ORDER_CART = "장바구니 신청 성공";

    public static final String ADD_BOOKMARK = "찜 등록 성공";
    public static final String READ_BOOKMARK = "찜 목록 조회 성공";
    public static final String NOT_FOUND_BOOKMARK = "찜 목록 없음";
    public static final String DELETE_BOOKMARK = "찜 목록 삭제 성공";

}
