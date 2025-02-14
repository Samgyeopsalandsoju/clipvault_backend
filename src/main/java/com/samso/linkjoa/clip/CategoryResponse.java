package com.samso.linkjoa.clip;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private  Long color;
}
