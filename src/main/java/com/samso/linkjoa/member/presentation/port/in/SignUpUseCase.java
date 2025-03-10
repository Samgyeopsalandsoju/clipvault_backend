package com.samso.linkjoa.member.presentation.port.in;

import com.samso.linkjoa.member.presentation.web.request.ReqSignUp;
import jakarta.servlet.http.HttpServletRequest;

public interface SignUpUseCase {
    String signUp(HttpServletRequest request, ReqSignUp reqSignUp);
}
