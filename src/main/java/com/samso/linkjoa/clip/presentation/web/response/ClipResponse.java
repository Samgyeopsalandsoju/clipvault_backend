package com.samso.linkjoa.clip.presentation.web.response;

import com.samso.linkjoa.category.presentation.web.response.CategoryResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

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
