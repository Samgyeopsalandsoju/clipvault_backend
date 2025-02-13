package com.samso.linkjoa.clip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
public class ClipResponse {
    private Long id;
    private String title;
    private String link;
    private String visible;
    private long forkedCount;
    private CategoryResponse category;

}
