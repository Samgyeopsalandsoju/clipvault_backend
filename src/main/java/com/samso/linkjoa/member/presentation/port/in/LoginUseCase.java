package com.samso.linkjoa.member.presentation.port.in;

import com.samso.linkjoa.member.presentation.web.request.ReqLogin;

public interface LoginUseCase {
    String authenticate(ReqLogin reqLogin);
}
