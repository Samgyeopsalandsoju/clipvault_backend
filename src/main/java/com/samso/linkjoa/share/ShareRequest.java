package com.samso.linkjoa.share;

import lombok.Value;

@Value
public class ShareRequest {

    Long id;
    String title;
    String link;
    String due;
}
