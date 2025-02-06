package com.samso.linkjoa.domain.Authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthenticationEnum {

    SEND_AUTH_INFO_SUCCESS("2000"),
    SEND_AUTH_INFO_FAIL("2001"),
    AUTH_SUCCESS("2002"),
    AUTH_FAIL("2003");

    private final String value;
}
