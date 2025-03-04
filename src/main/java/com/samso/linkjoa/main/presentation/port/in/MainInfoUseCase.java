package com.samso.linkjoa.main.presentation.port.in;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface MainInfoUseCase {

    long getClipTotalCount();
    long getShareTotalCount();
    List<Long> getForkedList(HttpServletRequest request);
}
