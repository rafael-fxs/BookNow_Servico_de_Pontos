package com.booknow.pontos.config;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Claims claims;

    public JwtAuthenticationToken(Claims claims) {
        super(null);
        this.claims = claims;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject(); // Pode ser o identificador do usuário
    }

}
