package com.samso.linkjoa.category.presentation.web.request;

import lombok.Value;

@Value
public class CategoryRequest {

    Long id;
    String name;
    int color;
}
