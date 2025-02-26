package com.samso.linkjoa.clip.presentation.web.request;

import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import lombok.Value;

@Value
public class ReqClip {

    Long id;
    String title;
    String link;
    String visible;
    ReqCategory category;
}
