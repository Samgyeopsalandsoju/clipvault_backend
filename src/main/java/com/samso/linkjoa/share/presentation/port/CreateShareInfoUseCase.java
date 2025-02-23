package com.samso.linkjoa.share.presentation.port;

import com.samso.linkjoa.share.presentation.web.request.ReqShare;
import jakarta.servlet.http.HttpServletRequest;

public interface CreateShareInfoUseCase {

    String createLink(HttpServletRequest request, ReqShare share);
}
