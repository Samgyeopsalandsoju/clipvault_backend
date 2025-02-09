package com.samso.linkjoa.domain.Authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthenticationEnum {

    SEND_AUTH_INFO_SUCCESS("2000"), //인증번호 발송 성공
    SEND_AUTH_INFO_FAIL("2001"), //인증번호 발송 실패
    NOT_EXIST_AUTH_INFO("2002"), //인증정보 없음
    AUTH_SUCCESS("2003"), // 인증성공
    AUTH_FAIL("2004"); // 인증실패

    private final String value;
}
