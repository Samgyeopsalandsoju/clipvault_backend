package com.samso.linkjoa.authentication.presentation.port.in;

import com.samso.linkjoa.authentication.presentation.web.request.ReqAuthentication;

public interface AuthenticationUseCase {
    String initAuthentication(String mail);
    String verifyAuthentication(ReqAuthentication reqAuthentication);
}
