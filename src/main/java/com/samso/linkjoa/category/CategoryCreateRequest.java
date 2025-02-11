package com.samso.linkjoa.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Value;

@Value
public class CategoryCreateRequest {

    Long id;
    String name;
    int color;
}
