package com.samso.linkjoa.authentication.application.service;

import com.samso.linkjoa.authentication.domain.AuthenticationEnum;
import com.samso.linkjoa.authentication.domain.entity.Authentication;
import com.samso.linkjoa.authentication.presentation.port.in.AuthenticationUseCase;
import com.samso.linkjoa.authentication.presentation.web.request.ReqAuthentication;
import com.samso.linkjoa.core.common.exception.ApplicationInternalException;
import com.samso.linkjoa.core.utility.Encryptor;
import com.samso.linkjoa.core.infrastructure.mail.MailSender;
import com.samso.linkjoa.core.infrastructure.redis.RedisOffSetEnum;
import com.samso.linkjoa.core.infrastructure.redis.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthenticationUseCase {
    private final Authentication authentication;
    private final RedisRepository redisRepository;
    private final MailSender mailSender;
    @Override
    public String initAuthentication(String mail) {

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

    @Override
    public String verifyAuthentication(ReqAuthentication reqAuthentication) {
        //인증 키 있는지 확인
        //Assert.notNull(request.getSession().getAttribute("mailAuth"), AuthenticationEnum.NOT_EXIST_AUTH_INFO.getValue());
        Optional.ofNullable(reqAuthentication.getAuthKey())
                .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.NOT_EXIST_AUTH_INFO.getValue(), "no history of authentication attempts"));

        //레디스 조회
        Optional<Map<Object,Object>> storedData = redisRepository.getHashData(reqAuthentication.getAuthKey());

        //레디스 데이터 있는지 조회
        storedData.map(m-> m.get("mail"))
                .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.EXPIRED_AUTH_INFO.getValue(), "Expired Authentication mail"));

        String mail = storedData.get().get("mail").toString();
        //입력한 mail-code 와 redis 저장된 mail-code 비교
        storedData
                .filter(data -> reqAuthentication.getMail().equals(Encryptor.twoWayDecrypt(mail))
                        && reqAuthentication.getAuthCode().equals(Encryptor.twoWayDecrypt(data.get("code").toString())))
                .orElseThrow(() -> new ApplicationInternalException(AuthenticationEnum.AUTH_FAIL.getValue(), "Authentication failed"));

        return mail;
    }
}
