package com.samso.linkjoa.clip.presentation.port.in;

import jakarta.servlet.http.HttpServletRequest;

public interface DeleteClipUseCase {
    String deleteClipById(HttpServletRequest request, Long clipId);
}
