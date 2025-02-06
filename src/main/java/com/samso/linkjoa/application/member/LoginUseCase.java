package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.member.MemberEnum;
import com.samso.linkjoa.domain.member.MemberRepository;
import com.samso.linkjoa.presentation.member.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class LoginUseCase {
    private final MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String login(HttpServletRequest request, LoginRequest loginRequest) {

        String mail = loginRequest.getMail();
        String password = loginRequest.getPassword();

        return memberRepository.findByMail(mail)
                .map(member -> Encryptor.matchOneWay(password, member.getPassword()) ? MemberEnum.LOGIN_SUCCESS.getValue() : MemberEnum.PASSWORD_MISMATCH.getValue())
                .orElseThrow(() -> new AuthenticationException(MemberEnum.NOT_JOINED_USER.name()) {
                });
    }
}
