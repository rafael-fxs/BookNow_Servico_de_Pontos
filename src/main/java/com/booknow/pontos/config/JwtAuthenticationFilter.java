package com.booknow.pontos.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.startsWith("/swagger-ui") || path.startsWith("/api-docs") || path.startsWith("/swagger-resources") || path.startsWith("/webjars")) {
            chain.doFilter(request, response);
            return;
        }


        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            // Bypass se o token for "123456"
            if ("123456".equals(token)) {
                Claims bypassClaims = Jwts.claims();
                bypassClaims.setSubject("bypassUser");
                bypassClaims.put("roles", "ROLE_BYPASS");

                JwtAuthenticationToken bypassAuthentication = new JwtAuthenticationToken(bypassClaims);
                bypassAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(bypassAuthentication);

                chain.doFilter(request, response);
                return;
            }

            try {
                // Valida o token JWT e extrai as Claims
                Claims claims = jwtUtil.parseToken(token);

                // Configura a autenticação no contexto do Spring Security, se necessário
                if (claims != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    JwtAuthenticationToken authentication = new JwtAuthenticationToken(claims);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // Em caso de falha na validação do token, envia erro de autorização
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado");
                return;
            }
        } else if (token == null) {
            // Em caso de ausência de token, opcionalmente pode-se enviar erro de autorização
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token não encontrado");
            return;
        }

        // Continua com a cadeia de filtros
        chain.doFilter(request, response);
    }
}