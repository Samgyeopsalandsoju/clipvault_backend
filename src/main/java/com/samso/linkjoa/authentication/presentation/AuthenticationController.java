package com.samso.linkjoa.authentication.presentation;

import com.samso.linkjoa.authentication.presentation.port.in.AuthenticationUseCase;
import com.samso.linkjoa.authentication.presentation.web.request.ReqAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/v1/auth/mail-sending")
    public @ResponseBody String initAuth(HttpServletRequest request, @RequestBody ReqAuthentication reqAuthentication) throws Exception {

        return authenticationUseCase.initAuthentication(reqAuthentication.getMail());
    }

    @PostMapping("/v1/auth/mail-verification")
    public @ResponseBody String verifyAuth(HttpServletRequest request, @RequestBody ReqAuthentication reqAuthentication) throws Exception{

        return authenticationUseCase.verifyAuthentication(reqAuthentication);
    }
}
