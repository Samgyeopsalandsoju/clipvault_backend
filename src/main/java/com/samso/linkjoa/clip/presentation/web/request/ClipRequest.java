package com.samso.linkjoa.clip.presentation.web.request;

import com.samso.linkjoa.category.presentation.web.request.CategoryRequest;
import lombok.Value;

@Value
public class ClipRequest {

    Long id;
    String title;
    String link;
    String visible;
    CategoryRequest category;
}
