package com.samso.linkjoa.presentation.member;

import com.samso.linkjoa.application.member.LoginUseCase;
import com.samso.linkjoa.presentation.member.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginUseCase loginUseCase;

//    @PostMapping("/login")
//    public @ResponseBody String login(HttpServletRequest request, @RequestBody LoginRequest loginRequest){
//
//        return loginUseCase.login(request, loginRequest);
//    }

    //로그인 후 JWT 토큰 발급
    @PostMapping("/authenticate.do")
    public String authenticate(@RequestBody LoginRequest loginRequest){
        return loginUseCase.authenticate(loginRequest);
    }
}
