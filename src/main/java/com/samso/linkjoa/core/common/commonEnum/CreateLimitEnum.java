package com.samso.linkjoa.core.common.commonEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum CreateLimitEnum {

    CATEGORY(10),
    CLIP(100),
    FORK(20),
    SHARE(10),
    OVER(99999)
    ;
    private final long value;
}
