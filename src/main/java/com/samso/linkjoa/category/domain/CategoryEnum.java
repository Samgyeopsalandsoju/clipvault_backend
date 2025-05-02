package com.samso.linkjoa.category.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryEnum {

    EDIT_CATEGORY_SUCCESS("6000"),
    EDIT_CATEGORY_FAIL("6001"),
    DELETE_CATEGORY_SUCCESS("6002"),
    DELETE_CATEGORY_INFO_EMPTY("6003"),
    NOT_FOUND_CATEGORY("6004"),
    CREATE_CATEGORY_SUCCESS("6005"),
    MODIFY_CATEGORY_SUCCESS("6006")
    ;
    private final String value;
}
