package com.samso.linkjoa.clip.presentation.web.response;

import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResClip {
    private Long id;
    private String title;
    private String link;
    private String visible;
    private long forkedCount;
    private ResCategory category;

}
