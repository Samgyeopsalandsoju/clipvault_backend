package com.samso.linkjoa.share;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ShareResponse {

    private Long id;
    private String title;
    private String link;
    private LocalDateTime due;
}
