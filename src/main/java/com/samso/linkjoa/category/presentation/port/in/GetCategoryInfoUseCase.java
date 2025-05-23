package com.samso.linkjoa.category.presentation.port.in;

import com.samso.linkjoa.category.presentation.web.response.ResCategory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GetCategoryInfoUseCase {

    List<ResCategory> getCategoryList(HttpServletRequest request);
}
