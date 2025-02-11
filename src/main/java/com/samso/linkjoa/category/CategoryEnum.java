package com.samso.linkjoa.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryEnum {

    CREATE_FAIL("3001");
    private final String value;
}
