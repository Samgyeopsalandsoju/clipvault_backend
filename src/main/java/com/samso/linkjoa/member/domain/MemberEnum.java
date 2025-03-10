package com.samso.linkjoa.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MemberEnum {

    //TODO 불필요 enum 제거
    SIGN_UP_SUCCESS("1000"),  //회원가입 성공
    LOGIN_SUCCESS("1001"),   //로그인 성공
    NOT_EXIST_VERIFY_INFO("1002"), //메일 인증 이력 없음
    DIFFERENT_MAIL_OF_VERIFIED_MAIL("1003"),//인증된 메일과 회원가입 시도한 메일이 불일치
    ALREADY_JOINED_USER("1004"),//이미 가입한 사용자
    NOT_FOUND_USER("1005"),//회원정보 없음
    PASSWORD_MISMATCH("1006"),//비밀번호 불일치

    TOKEN_IS_MISSING("9999");
    private final String value;
}
