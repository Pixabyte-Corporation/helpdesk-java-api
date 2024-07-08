package com.pixabyte.helpdeskapi.authentication.infrastructure.security;

import com.pixabyte.helpdeskapi.authentication.domain.TokenGenerator;
import com.pixabyte.helpdeskapi.authentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenGenerator implements TokenGenerator {

    private final String jwtSecret;
    private final Long jwtExpiration;

    public JwtTokenGenerator(
            @Value("${helpdesk.security.jwt.secret}") String jwtSecret,
            @Value("${helpdesk.security.jwt.expiration}") Long jwtExpiration
    ) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    @Override
    public String generate(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigninKey())
                .compact();
    }

    private Key getSigninKey() {
        byte[] secretBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

}
