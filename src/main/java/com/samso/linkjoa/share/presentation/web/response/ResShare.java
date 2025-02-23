package com.samso.linkjoa.share.presentation.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResShare {

    private Long id;
    private String title;
    private String link;
    private LocalDateTime due;
}
