package com.springapplication.security.jwt;

import com.springapplication.model.Role;
import com.springapplication.model.User;
import com.springapplication.service.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.accessExpired}")
    private long accessExpired;

    @Value("${jwt.token.refreshExpired}")
    private long refreshExpired;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createAccessToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", getRoleNames(user.getRoles()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessExpired);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String createRefreshToken() {

        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshExpired);

        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        return new JwtAuthToken(token,
                getRoles(token).stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }

    public List<String> getRoles(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();


        return (List<String>) claims.get("roles");
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

    private List<String> getRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();
        userRoles.forEach(role -> result.add(role.getName()));

        return result;
    }
}
