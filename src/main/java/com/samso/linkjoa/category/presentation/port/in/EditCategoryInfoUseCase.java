package com.samso.linkjoa.category.presentation.port.in;

import com.samso.linkjoa.category.presentation.web.request.ReqCategory;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EditCategoryInfoUseCase {

    String editCategoryListInfo(HttpServletRequest request, List<ReqCategory> reqCategoryList);
}
