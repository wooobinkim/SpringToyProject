package com.compnay.stp.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    @Value("${token.secret}")
    private String secretKey;

    @Value("${token.access_token.expiration_time}")
    private String expirationTime;

    //회원가입시 토큰생성
    public String createToken(Long userId, String role){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("ROLE", role)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.valueOf(expirationTime)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


}
