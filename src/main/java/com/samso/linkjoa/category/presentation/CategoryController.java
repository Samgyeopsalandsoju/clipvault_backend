package com.samso.linkjoa.category.presentation;

import com.samso.linkjoa.category.presentation.port.in.EditCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.port.in.GetCategoryInfoUseCase;
import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class CategoryController {

    private final GetCategoryInfoUseCase getCategoryInfoUseCase;
    private final EditCategoryInfoUseCase editCategoryInfoUseCase;
    @GetMapping("/v1/category/list")
    public @ResponseBody List<ResCategory> getCategoryList(HttpServletRequest request){

        return getCategoryInfoUseCase.getCategoryList(request);
    }

    @PostMapping("/v1/category/edit")
    public String editCategoryInfo(HttpServletRequest request, @RequestBody List<ReqCategory> reqCategoryList){

        return editCategoryInfoUseCase.editCategoryListInfo(request, reqCategoryList);
    }
}
