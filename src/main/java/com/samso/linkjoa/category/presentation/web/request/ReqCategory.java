package com.samso.linkjoa.category.presentation.web.request;

import lombok.Value;

@Value
public class ReqCategory {

    String id;
    String name;
    int color;
    int sortOrder;
}
