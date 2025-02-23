package com.samso.linkjoa.share.presentation.port;

import jakarta.servlet.http.HttpServletRequest;

public interface DeleteShareInfoUseCase {

    String deleteLinkById(HttpServletRequest request, Long linkId);
}
