package com.samso.linkjoa.member.presentation;

import com.samso.linkjoa.member.presentation.port.in.LoginUseCase;
import com.samso.linkjoa.member.presentation.port.in.SignUpUseCase;
import com.samso.linkjoa.member.presentation.web.request.ReqLogin;
import com.samso.linkjoa.member.presentation.web.request.ReqSignUp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class MemberController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;

    @PostMapping("/v1/member/sign-up")
    public @ResponseBody String signUp(HttpServletRequest request, @RequestBody ReqSignUp reqSignUp) throws Exception{
        return signUpUseCase.signUp(request, reqSignUp);
    }

    //로그인 후 JWT 토큰 발급
    @PostMapping("/v1/member/login")
    public String authenticate(@RequestBody ReqLogin reqLogin){
        return loginUseCase.authenticate(reqLogin);
    }
}
