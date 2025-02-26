package com.samso.linkjoa.category.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryEnum {

    EDIT_CATEGORY_SUCCESS("6000"),
    EDIT_CATEGORY_FAIL("6001")
    ;
    private final String value;
}
