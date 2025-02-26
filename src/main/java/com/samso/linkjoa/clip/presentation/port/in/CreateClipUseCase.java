package com.samso.linkjoa.clip.presentation.port.in;

import com.samso.linkjoa.clip.presentation.web.request.ReqClip;
import jakarta.servlet.http.HttpServletRequest;

public interface CreateClipUseCase {

    String createClip(HttpServletRequest request, ReqClip reqClip);
}
