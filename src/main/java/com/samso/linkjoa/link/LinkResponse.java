package com.samso.linkjoa.link;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LinkResponse {

    private Long id;
    private String title;
    private String link;
    private LocalDateTime due;
}
