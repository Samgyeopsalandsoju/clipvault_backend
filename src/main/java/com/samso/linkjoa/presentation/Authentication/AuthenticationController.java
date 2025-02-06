package com.samso.linkjoa.presentation.Authentication;

import com.samso.linkjoa.application.Authentication.AuthenticationUseCase;
import com.samso.linkjoa.core.common.ApiResponse;
import com.samso.linkjoa.presentation.Authentication.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationUseCase authenticationUseCase;

    @PostMapping("/init")
    public @ResponseBody String initAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest){

        return authenticationUseCase.initAuthentication(request, authenticationRequest.getMail());
    }

    @PostMapping("/verify")
    public @ResponseBody String verifyAuth(HttpServletRequest request, @RequestBody AuthenticationRequest authenticationRequest){

        return authenticationUseCase.verifyAuthentication(request, authenticationRequest);
    }
}
