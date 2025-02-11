package com.samso.linkjoa.category;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class CategoryController {

    private CategoryUsecase categoryUseCase;

    @PostMapping("/v1/category/create")
    public @ResponseBody CategoryCreateResponse categoryCreate(HttpServletRequest request, @RequestBody CategoryCreateRequest categoryCreateRequest){

        return categoryUseCase.createCategory(request, categoryCreateRequest);
    }
}
