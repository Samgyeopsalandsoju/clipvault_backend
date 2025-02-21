package com.samso.linkjoa.category.presentation;

import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.response.CategoryResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController {

    private final GetCategoryInfoUseCase getCategoryInfoUseCase;
    @GetMapping("/v1/category/list")
    public @ResponseBody List<CategoryResponse> getCategoryList(HttpServletRequest request){

        return getCategoryInfoUseCase.getCategoryList(request);
    }
}
