package com.samso.linkjoa.fork.presentation.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResFork {

    private long id;
    private long categoryColor;
    private String categoryName;
    private String clipLink;
    private String clipTitle;
}
