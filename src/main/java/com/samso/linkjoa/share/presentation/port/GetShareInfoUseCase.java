package com.samso.linkjoa.share.presentation.port;

import com.samso.linkjoa.share.presentation.web.response.ResShare;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GetShareInfoUseCase {

    List<ResShare> getLinkList(HttpServletRequest request);
}
