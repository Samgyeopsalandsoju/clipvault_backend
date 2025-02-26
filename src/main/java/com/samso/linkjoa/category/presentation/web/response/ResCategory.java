package com.samso.linkjoa.category.presentation.web.response;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResCategory {
    private String id;
    private String name;
    private int color;
    private int order;
}
