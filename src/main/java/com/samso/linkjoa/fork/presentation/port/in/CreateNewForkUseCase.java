package com.samso.linkjoa.fork.presentation.port.in;

import com.samso.linkjoa.fork.presentation.web.request.ReqFork;
import jakarta.servlet.http.HttpServletRequest;

public interface CreateNewForkUseCase {


    String createFork(HttpServletRequest request, ReqFork reqFork);
}
