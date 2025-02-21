package com.samso.linkjoa.clip.presentation.port.in;

import com.samso.linkjoa.clip.presentation.web.request.ClipRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface CreateClipUseCase {

    String createClip(HttpServletRequest request, ClipRequest clipRequest);
}
