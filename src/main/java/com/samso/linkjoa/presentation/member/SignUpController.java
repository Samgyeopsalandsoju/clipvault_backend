package com.samso.linkjoa.presentation.member;

import com.samso.linkjoa.application.member.SignUpUseCase;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.presentation.member.request.SignUp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/api/signUp")
    public @ResponseBody Member signUp(HttpServletRequest request, @RequestBody SignUp signUpRequest){

        return signUpUseCase.signUp(request, signUpRequest.getMail(), signUpRequest.getPassword());
    }
}
