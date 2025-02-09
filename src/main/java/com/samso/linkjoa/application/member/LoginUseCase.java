package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.presentation.member.request.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LoginUseCase {
    //private final MemberRepository memberRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;


//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public String login(HttpServletRequest request, LoginRequest loginRequest) {
//
//        String mail = Encryptor.twoWayEncrypt(loginRequest.getMail());
//        String password = loginRequest.getPassword();
//
//        Member member = memberRepository.findByMail(mail)
//                .orElseThrow(() -> new EntityNotFoundException(MemberEnum.NOT_FOUND_USER.getValue()));
//
//        Optional.of(member)
//                .filter(m -> Encryptor.matchOneWay(password, m.getPassword()))
//                .orElseThrow(() -> new IllegalArgumentException(MemberEnum.PASSWORD_MISMATCH.getValue()));
//
//        return MemberEnum.LOGIN_SUCCESS.getValue();
//    }

    public String authenticate(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getMail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(authentication);;

        return token;
    }
}
