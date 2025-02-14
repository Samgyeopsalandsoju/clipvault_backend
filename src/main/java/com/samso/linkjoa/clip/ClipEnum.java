package com.samso.linkjoa.clip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ClipEnum {

    CREATE_CLIP_SUCCESS("3001"),
    CLIP_EMPTY("3002"),
    MODIFY_CLIP_SUCCESS("3003");
    private final String value;
}
