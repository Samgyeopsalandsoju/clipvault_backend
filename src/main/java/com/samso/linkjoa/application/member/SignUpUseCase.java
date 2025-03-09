package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.utility.Encryptor;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.core.springSecurity.Role;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.domain.member.MemberEnum;
import com.samso.linkjoa.domain.member.MemberRepository;
import com.samso.linkjoa.presentation.member.request.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SignUpUseCase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String signUp(HttpServletRequest request, SignUpRequest signUpRequest) throws Exception{

        //세션에서 메일정보확인
        String mail = signUpRequest.getVerifiedMail();
        //null 체크
        //Assert.notNull(verifiedMail, MemberEnum.NOT_EXIST_VERIFY_INFO.getValue());
        Optional.ofNullable(mail)
                .orElseThrow(() -> new ApplicationInternalException(MemberEnum.NOT_EXIST_VERIFY_INFO.getValue(), "No mail information in session"));

        //인증한 메일과 회원가입 시도한 메일 정보가 일치하는지 확인
        Optional.of(Encryptor.twoWayDecrypt(mail))
                .filter(m -> m.equals(signUpRequest.getMail()))
                .orElseThrow(() -> new ApplicationInternalException(MemberEnum.DIFFERENT_MAIL_OF_VERIFIED_MAIL.getValue(), "The verified email and the email information attempted to sign up do not match.") {
                });

        String encryptedMail = Encryptor.twoWayEncrypt(signUpRequest.getMail());
        //이미 가입한 회원인지 확인
        memberRepository.findByMail(encryptedMail)
                .ifPresent(member ->
                   {throw new ApplicationInternalException(MemberEnum.ALREADY_JOINED_USER.getValue(), "Already a registered member");});

        //회원가입 save
        Member member = new Member(encryptedMail, passwordEncoder.encode(signUpRequest.getPassword()), Role.USER);
        memberRepository.save(member);

        return MemberEnum.SIGN_UP_SUCCESS.getValue();
    }
}
