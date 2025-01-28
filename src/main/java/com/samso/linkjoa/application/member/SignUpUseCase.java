package com.samso.linkjoa.application.member;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.domain.Member;
import com.samso.linkjoa.domain.MemberRepository;
//import com.samso.linkjoa.infrastructure.external.SSHDatabaseConnection;
import com.samso.linkjoa.presentation.member.request.SignUp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SignUpUseCase {
    private final MemberRepository memberRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Member signUp(String mail, String password) {

        //1. 유효성 검사
        //2. 이메일 인증 여부 확인;
        //redis : 인증된 이메일이 있는지?
        //이메일 있는지 확인은 인증메일 보낼때;

        //3. 회원가입
        //save
        Member member = new Member(Encryptor.twoWayEncrypt(mail), Encryptor.oneWayEncrypt(password));
        //4. 멤버
        //

        //4. 성공하면 로그인 정보를 session 에 담자
        return memberRepository.save(member);
    }
}
