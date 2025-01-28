package com.samso.linkjoa.presentation.member;

import com.samso.linkjoa.application.member.SignUpUseCase;
import com.samso.linkjoa.domain.Member;
import com.samso.linkjoa.presentation.member.request.SignUp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/signUp")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping
    public Member signUp(@RequestBody SignUp signUpRequest){

        return signUpUseCase.signUp(signUpRequest.getMail(), signUpRequest.getPassword());
    }
}
