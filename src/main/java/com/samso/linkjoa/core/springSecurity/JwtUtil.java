package com.samso.linkjoa.core.springSecurity;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    //TODO(key 별도 관리 필요)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long validityInMilliseconds = 1800000;

    //Jwt 토큰 생성
    public String generateToken(Authentication authentication){

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                //.setSubject(String.valueOf(memberId))
                .setClaims(Map.of("memberId", userDetails.getMemberId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    //Jwt 토큰 검증
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJwt(token);
            return true;
        } catch (Exception e){
            //TODO exception  처리
        }
        return false;
    }

    //JWT 토큰에서 사용자 정보 추출
    public Long getMemberIdFromToken(String token){
//        return Long.valueOf(Jwts.parserBuilder()
//                        .setSigningKey(key)
//                        .build()
//                        .parseClaimsJwt(token)
//                        .getBody()
//                        .getSubject());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .get("userId", Long.class);
    }
}
