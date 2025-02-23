package com.samso.linkjoa.share.presentation.web.request;

import lombok.Value;

@Value
public class ReqShare {

    Long id;
    String title;
    String link;
    String due;
}
