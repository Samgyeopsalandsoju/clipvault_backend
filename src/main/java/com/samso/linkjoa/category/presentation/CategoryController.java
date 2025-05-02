package com.samso.linkjoa.category.presentation;

import com.samso.linkjoa.category.presentation.port.in.DeleteCategoryInfoUseCase;
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
    private final DeleteCategoryInfoUseCase deleteCategoryInfoUseCase;
    @GetMapping("/v1/category/list")
    public @ResponseBody List<ResCategory> getCategoryList(HttpServletRequest request){

        return getCategoryInfoUseCase.getCategoryList(request);
    }

    @PostMapping("/v1/category/post")
    public String editCategoryInfo(HttpServletRequest request, @RequestBody List<ReqCategory> reqCategoryList){

        return editCategoryInfoUseCase.editCategoryListInfo(request, reqCategoryList);
    }

    @DeleteMapping("/v1/category/delete/{categoryId}")
    public String deleteCategoryInfo(HttpServletRequest request, @PathVariable String categoryId){

        return deleteCategoryInfoUseCase.deleteCategoryById(request, categoryId);
    }

    @PostMapping("/v1/category/create")
    public String createCategory(HttpServletRequest request, @RequestBody ReqCategory reqCategory){

        return editCategoryInfoUseCase.createCategory(request, reqCategory);
    }

    @PatchMapping("/v1/category/modify")
    public String modifyCategory(HttpServletRequest request, @RequestBody ReqCategory reqCategory){

        return editCategoryInfoUseCase.modifyCategory(request, reqCategory);
    }
}
