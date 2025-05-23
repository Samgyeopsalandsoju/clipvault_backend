package com.samso.linkjoa.fork.presentation.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResFork {

    private long id;
    private String categoryColor;
    private String categoryName;
    private String clipLink;
    private String clipTitle;
    private long clipId;
}
