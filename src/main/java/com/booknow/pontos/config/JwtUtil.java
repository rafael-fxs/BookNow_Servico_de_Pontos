package com.booknow.pontos.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    public Claims parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        return Jwts.parserBuilder()
                .setSigningKey(key)          // Define a chave para validação
                .build()                     // Constrói o JwtParser
                .parseClaimsJws(token)       // Faz o parsing do token
                .getBody();                  // Extrai as Claims do token
    }
}
