package com.samso.linkjoa.member.application.service;

import com.samso.linkjoa.core.springSecurity.JwtUtil;
import com.samso.linkjoa.core.utility.Encryptor;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.Role;
import com.samso.linkjoa.member.application.port.out.repository.MemberRepository;
import com.samso.linkjoa.member.domain.entity.Member;
import com.samso.linkjoa.member.domain.MemberEnum;
import com.samso.linkjoa.member.presentation.port.in.LoginUseCase;
import com.samso.linkjoa.member.presentation.port.in.SignUpUseCase;
import com.samso.linkjoa.member.presentation.web.request.ReqLogin;
import com.samso.linkjoa.member.presentation.web.request.ReqSignUp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements SignUpUseCase, LoginUseCase {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Override
    public String signUp(HttpServletRequest request, ReqSignUp reqSignUp) {
        //세션에서 메일정보확인
        String mail = reqSignUp.getVerifiedMail();
        //null 체크
        Optional.ofNullable(mail)
                .orElseThrow(() -> new ApplicationInternalException(MemberEnum.NOT_EXIST_VERIFY_INFO.getValue(), "No mail information in session"));

        //인증한 메일과 회원가입 시도한 메일 정보가 일치하는지 확인
        Optional.of(Encryptor.twoWayDecrypt(mail))
                .filter(m -> m.equals(reqSignUp.getMail()))
                .orElseThrow(() -> new ApplicationInternalException(MemberEnum.DIFFERENT_MAIL_OF_VERIFIED_MAIL.getValue(), "The verified email and the email information attempted to sign up do not match.") {
                });

        String encryptedMail = Encryptor.twoWayEncrypt(reqSignUp.getMail());
        //이미 가입한 회원인지 확인
        memberRepository.findByMail(encryptedMail)
                .ifPresent(member ->
                {throw new ApplicationInternalException(MemberEnum.ALREADY_JOINED_USER.getValue(), "Already a registered member");});

        //회원가입 save
        Member member = new Member(encryptedMail, passwordEncoder.encode(reqSignUp.getPassword()), Role.USER);
        memberRepository.save(member);

        return MemberEnum.SIGN_UP_SUCCESS.getValue();
    }
    @Override
    public String authenticate(ReqLogin reqLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(reqLogin.getMail(), reqLogin.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(authentication);;

        return token;
    }
}
