package com.samso.linkjoa.category.presentation.port.in;


import jakarta.servlet.http.HttpServletRequest;

public interface DeleteCategoryInfoUseCase {

    String deleteCategoryById(HttpServletRequest request, String categoryId);
}
