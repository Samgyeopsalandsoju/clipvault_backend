package com.samso.linkjoa.link;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@RequiredArgsConstructor
public enum LinkEnum {
    CREATE_LINK_SUCCESS("4001"),
    NOT_FOUND_LINK("4002");
    private final String value;
}
