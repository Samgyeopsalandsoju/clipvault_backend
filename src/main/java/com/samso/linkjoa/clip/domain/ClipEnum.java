package com.samso.linkjoa.clip.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClipEnum {

    CREATE_CLIP_SUCCESS("3001"),
    CLIP_EMPTY("3002"),
    MODIFY_CLIP_SUCCESS("3003"),
    NOT_FOUND_CLIP("3004"),
    DELETE_SUCCESS("3005"),
    DELETE_FAIL("3006"),
    NOT_FOUND_CATEGORY("3007")
    ;

    private final String value;
}
