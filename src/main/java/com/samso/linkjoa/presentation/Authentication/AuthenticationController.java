package com.samso.linkjoa.presentation.Authentication;

import com.samso.linkjoa.application.Authentication.AuthenticationUseCase;
import com.samso.linkjoa.presentation.Authentication.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/v1/auth/mail-sending")
    public @ResponseBody String initAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        return authenticationUseCase.initAuthentication(authenticationRequest.getMail());
    }

    @PostMapping("/v1/auth/mail-verification")
    public @ResponseBody String verifyAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        return authenticationUseCase.verifyAuthentication(authenticationRequest);
    }
}
