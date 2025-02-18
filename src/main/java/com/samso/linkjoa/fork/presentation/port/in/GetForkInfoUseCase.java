package com.samso.linkjoa.fork.presentation.port.in;

import com.samso.linkjoa.fork.domain.entity.Fork;
import com.samso.linkjoa.fork.presentation.web.response.ResFork;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface GetForkInfoUseCase {

    List<ResFork> getForkList(HttpServletRequest request);
}
