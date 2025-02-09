package com.samso.linkjoa.presentation.Authentication;

import com.samso.linkjoa.application.Authentication.AuthenticationUseCase;
import com.samso.linkjoa.presentation.Authentication.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/init.do")
    public @ResponseBody String initAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        return authenticationUseCase.initAuthentication(request, authenticationRequest.getMail());
    }

    @PostMapping("/verify.do")
    public @ResponseBody String verifyAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        return authenticationUseCase.verifyAuthentication(request, authenticationRequest);
    }
}
