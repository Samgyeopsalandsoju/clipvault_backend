package com.samso.linkjoa.fork.presentation.port.in;

import jakarta.servlet.http.HttpServletRequest;

public interface DeleteForkUseCase {

    String deleteForkClip(HttpServletRequest request, long forkId);
}
