package com.samso.linkjoa.domain.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Authentication {

    private String authKey;
    private String mail;
    private long authCode;

    public Authentication generateAuthCode(String mail){

        String authKey = UUID.randomUUID().toString();
        long generateCode = ThreadLocalRandom.current().nextInt(100000, 1000000);
        System.out.println("mail : " + mail);
        System.out.println("code : " + generateCode);

        return new Authentication(authKey, mail, generateCode);

    }
}
