package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.domain.member.MemberEnum;
import com.samso.linkjoa.domain.member.MemberRepository;
import com.samso.linkjoa.presentation.member.request.LoginRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LoginUseCase {
    private final MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String login(HttpServletRequest request, LoginRequest loginRequest) {

        String mail = Encryptor.twoWayEncrypt(loginRequest.getMail());
        String password = loginRequest.getPassword();

        Member member = memberRepository.findByMail(mail)
                .orElseThrow(() -> new EntityNotFoundException(MemberEnum.NOT_JOINED_USER.getValue()));

        Optional.of(member)
                .filter(m -> Encryptor.matchOneWay(password, m.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException(MemberEnum.PASSWORD_MISMATCH.getValue()));

        return MemberEnum.LOGIN_SUCCESS.getValue();
    }
}
