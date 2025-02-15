package com.samso.linkjoa.link;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public enum LinkEnum {
    CREATE_LINK_SUCCESS("4001"),
    NOT_FOUND_LINK("4002"),
    DELETE_LINK_SUCCESS("4003"),
    NOT_FOUND_DELETE_LINK("40034")
    ;
    private final String value;
}
