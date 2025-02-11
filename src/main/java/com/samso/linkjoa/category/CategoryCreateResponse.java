package com.samso.linkjoa.category;

import lombok.*;

@Builder
@Value
public class CategoryCreateResponse {
    Long id;
    String name;
    int color;
}
