package com.example.user.config;

import com.example.user.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    //토큰생성
    public String makeToken(User user){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("mbti",user.getMbti());
        //claims.put("imgPath",user.getImgPath());
        System.out.println(user.getInterest());
        claims.put("interest",user.getInterest());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getId().toString())
                .setExpiration(new Date(
                        System.currentTimeMillis()
                                + (1000L * 60 * 60 * 24 * 30)
                ))
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public  TokenInfo parseToken(String token){
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parse(token)
                .getBody();
        return TokenInfo.builder()
                .id(body.get("id", Long.class))
                .email(body.get("email", String.class))
                .name(body.get("name", String.class))
                .mbti(body.get("mbti", String.class))
                .build();
    }
}
