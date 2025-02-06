package com.samso.linkjoa.presentation.member;

import com.samso.linkjoa.application.member.LoginUseCase;
import com.samso.linkjoa.presentation.member.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/api/login")
    public @ResponseBody String login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){

        return loginUseCase.login(request, loginRequest);
    }
}
