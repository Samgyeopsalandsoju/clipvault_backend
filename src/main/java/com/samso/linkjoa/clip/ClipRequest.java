package com.samso.linkjoa.clip;

import lombok.Value;

@Value
public class ClipRequest {

    Long id;
    String title;
    String link;
    String visible;
    CategoryRequest category;
}
