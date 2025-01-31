package com.samso.linkjoa.application.Authentication;

import com.samso.linkjoa.core.Utility.Encryptor;
import com.samso.linkjoa.core.common.ApiResponse;
import com.samso.linkjoa.domain.Authentication.Authentication;
import com.samso.linkjoa.domain.mail.MailSender;
import com.samso.linkjoa.infrastructure.redis.RedisRepository;
import com.samso.linkjoa.presentation.Authentication.request.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final Authentication authentication;
    private final RedisRepository redisRepository;
    private final MailSender mailSender;
    public ApiResponse<String> initAuthentication(HttpServletRequest request, String mail){

        //인증번호 생성
        Authentication authenticationInfo = authentication.generateAuthCode(mail);

        //인증정보 redis 저장
        String authKey = UUID.randomUUID().toString();
        Map<String, String> authData = new HashMap<>();
        authData.put("mail", Encryptor.twoWayEncrypt(authenticationInfo.getMail()));
        authData.put("code", Encryptor.twoWayEncrypt(String.valueOf(authenticationInfo.getAuthCode())));
        redisRepository.saveHashData(authKey, authData, 180);
        //redisRepository.saveDate(authKey, authValue, RedisOffSetEnum.AuthSignUp.getValue());

        //메일 발송
        String subject = "[인증번호 발송]";
        String body = "인증번호 [" + authenticationInfo.getAuthCode()+ "]를 입력하세요 (유효시간 : 3분)";
        if(!mailSender.sendMail(authenticationInfo.getMail(), subject, body)){
            //TODO response 처리 수정 필요 - success 로 전달됨
            return new ApiResponse<>("fail","인증번호 발송에 실패하였습니다");
        }
        //session 저장
        request.getSession().setAttribute("mailAuth", authKey);
        return new ApiResponse<>("success", "인증번호를 발송하였습니다.");
    }

    public ApiResponse<String> verifyAuthentication(HttpServletRequest request, AuthenticationRequest authenticationRequest) {

        Assert.notNull(request.getSession().getAttribute("mailAuth"), "인증 정보가 없습니다. 인증을 다시 시도해주세요.");
        String authKey = request.getSession().getAttribute("mailAuth").toString();

        Optional<Map<Object,Object>> storedData = redisRepository.getHashData(authKey);

        storedData
                .filter(data -> authenticationRequest.getMail().equals(Encryptor.twoWayDecrypt(data.get("mail").toString()))
                                && authenticationRequest.getAuthCode().equals(Encryptor.twoWayDecrypt(data.get("code").toString())))
                //TODO Exception 수정필요
                .orElseThrow(() -> new IllegalArgumentException("인증정보가 불일치합니다."));

        request.getSession().setAttribute("verifiedMail", storedData.get().get("mail"));
        return new ApiResponse<>("success", "인증에 성공했습니다.");
    }
}
