//package com.samso.linkjoa.member.presentation;
//
//import com.samso.linkjoa.member.presentation.port.in.SignUpUseCase;
//import com.samso.linkjoa.member.presentation.web.request.ReqSignUp;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@AllArgsConstructor
//@RestController
//public class SignUpController {
//
//    private final SignUpUseCase signUpUseCase;
//
//    @PostMapping("/v1/member/sign-up")
//    public @ResponseBody String signUp(HttpServletRequest request, @RequestBody ReqSignUp reqSignUp) throws Exception{
//
//        return signUpUseCase.signUp(request, reqSignUp);
//    }
//}
