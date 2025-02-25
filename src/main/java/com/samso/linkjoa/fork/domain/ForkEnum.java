package com.samso.linkjoa.fork.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForkEnum {

    CREATE_SUCCESS("5000"),
    NOT_FOUND_CLIP("5001"),
    FAIL_FOUND_FORK_LIST("5002"),
    DELETE_SUCCESS("5003"),
    DELETE_FAIL("5004"),
    ALREADY_FORKED_CLIP("5005"),
    OWN_CLIP("5006")
    ;

    private final String value;
}
