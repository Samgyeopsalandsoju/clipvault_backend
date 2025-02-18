package com.samso.linkjoa.fork.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForkEnum {

    CREATE_SUCCESS("5000"),
    NOT_FOUND_CLIP("5001"),
    FAIL_FOUND_FORK_LIST("5002")
    ;

    private final String value;
}
