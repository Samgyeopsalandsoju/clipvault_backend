package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.domain.member.MemberRepository;
//import com.samso.linkjoa.infrastructure.external.SSHDatabaseConnection;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
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
    public Member signUp(HttpServletRequest request, String mail, String password) {

        //세션에서 메일정보확인
        Object verifiedMail = request.getSession().getAttribute("verifiedMail");
        //null 체크
        Assert.notNull(verifiedMail, "인증정보가 없습니다.");
        //인증한 메일과 회원가입 시도한 메일 정보가 일치하는지 확인
        Optional.ofNullable(Encryptor.twoWayDecrypt(verifiedMail.toString()))
                .filter(m -> m.equals(mail))
                .orElseThrow(() -> new IllegalArgumentException("메일이 인증되지 않았습니다."));
        //3. 회원가입
        //save
        Member member = new Member(Encryptor.twoWayEncrypt(mail), Encryptor.oneWayEncrypt(password));
        //4. 성공하면 로그인 정보를 session 에 담자
        return memberRepository.save(member);
    }
}
