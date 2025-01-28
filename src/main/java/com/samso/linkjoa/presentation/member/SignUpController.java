package com.samso.linkjoa.presentation.member;

import com.samso.linkjoa.application.member.SignUpUseCase;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.presentation.member.request.SignUp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/api/signUp")
    public @ResponseBody Member signUp(@RequestBody SignUp signUpRequest){

        return signUpUseCase.signUp(signUpRequest.getMail(), signUpRequest.getPassword());
    }
}
