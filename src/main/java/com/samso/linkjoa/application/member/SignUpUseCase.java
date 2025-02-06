package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.domain.member.MemberEnum;
import com.samso.linkjoa.domain.member.MemberRepository;
//import com.samso.linkjoa.infrastructure.external.SSHDatabaseConnection;
import com.samso.linkjoa.presentation.member.request.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignUpUseCase {
    private final MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String signUp(HttpServletRequest request, SignUpRequest signUpRequest) {

        //세션에서 메일정보확인
        Object verifiedMail = request.getSession().getAttribute("verifiedMail");

        //null 체크
        Assert.notNull(verifiedMail, MemberEnum.NOT_EXIST_VERIFY_INFO.getValue());

        //인증한 메일과 회원가입 시도한 메일 정보가 일치하는지 확인
        Optional.ofNullable(Encryptor.twoWayDecrypt(verifiedMail.toString()))
                .filter(m -> m.equals(signUpRequest.getMail()))
                .orElseThrow(() -> new AuthenticationException(MemberEnum.DIFFERENT_MAIL_OF_VERIFIED_MAIL.getValue()) {
                });

        //회원가입 save
        Member member = new Member(Encryptor.twoWayEncrypt(signUpRequest.getMail()), Encryptor.oneWayEncrypt(signUpRequest.getPassword()));
        memberRepository.save(member);

        return MemberEnum.SIGN_UP_SUCCESS.getValue();
    }
}
