package com.samso.linkjoa.share.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShareEnum {
    CREATE_SHARE_LINK_SUCCESS("4001"),
    NOT_FOUND_SHARE_LINK("4002"),
    DELETE_SHARE_LINK_SUCCESS("4003"),
    NOT_FOUND_DELETE_SHARE_LINK("4004")
    ;
    private final String value;
}
