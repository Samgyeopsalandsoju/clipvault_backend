package com.samso.linkjoa.link;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class LinkRequest {

    Long id;
    String title;
    String link;
    String due;
}
