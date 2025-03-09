package com.samso.linkjoa.application.Authentication;

import com.samso.linkjoa.core.utility.Encryptor;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.domain.Authentication.Authentication;
import com.samso.linkjoa.domain.Authentication.AuthenticationEnum;
import com.samso.linkjoa.domain.mail.MailSender;
import com.samso.linkjoa.infrastructure.redis.RedisOffSetEnum;
import com.samso.linkjoa.infrastructure.redis.RedisRepository;
import com.samso.linkjoa.presentation.Authentication.request.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final Authentication authentication;
    private final RedisRepository redisRepository;
    private final MailSender mailSender;

    public String initAuthentication(String mail) throws Exception {

        //인증정보 생성
        Authentication authenticationInfo = authentication.generateAuthCode(mail);

        //인증정보 redis 저장
        Map<String, String> authData = new HashMap<>();
        authData.put("mail", Encryptor.twoWayEncrypt(authenticationInfo.getMail()));
        authData.put("code", Encryptor.twoWayEncrypt(String.valueOf(authenticationInfo.getAuthCode())));
        redisRepository.saveHashData(authenticationInfo.getAuthKey(), authData, RedisOffSetEnum.SIGN_UP.getValue());

        //메일 발송
        String subject = "[인증번호 발송]";
        String body = "인증번호 [" + authenticationInfo.getAuthCode()+ "]를 입력하세요 (유효시간 : 5분)";
        if(!mailSender.sendMail(authenticationInfo.getMail(), subject, body)){
            throw new ApplicationInternalException(AuthenticationEnum.SEND_AUTH_INFO_FAIL.getValue(),"Failed to send authentication number");
        }

        return authenticationInfo.getAuthKey();
    }

    public String verifyAuthentication(AuthenticationRequest authenticationRequest) throws Exception {

        //인증 키 있는지 확인
        //Assert.notNull(request.getSession().getAttribute("mailAuth"), AuthenticationEnum.NOT_EXIST_AUTH_INFO.getValue());
        Optional.ofNullable(authenticationRequest.getAuthKey())
                .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.NOT_EXIST_AUTH_INFO.getValue(), "no history of authentication attempts"));

        //레디스 조회
        Optional<Map<Object,Object>> storedData = redisRepository.getHashData(authenticationRequest.getAuthKey());

        //레디스 데이터 있는지 조회
        storedData.map(m-> m.get("mail"))
                        .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.EXPIRED_AUTH_INFO.getValue(), "Expired Authentication mail"));

        String mail = storedData.get().get("mail").toString();
        //입력한 mail-code 와 redis 저장된 mail-code 비교
        storedData
                .filter(data -> authenticationRequest.getMail().equals(Encryptor.twoWayDecrypt(mail))
                                && authenticationRequest.getAuthCode().equals(Encryptor.twoWayDecrypt(data.get("code").toString())))
                .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.AUTH_FAIL.getValue(), "Authentication failed"));

        return mail;
    }
}
