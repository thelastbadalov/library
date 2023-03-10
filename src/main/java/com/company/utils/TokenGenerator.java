package com.company.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.company.exception.TokenNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class TokenGenerator {
    @Value("${jwt-variables.KEY}")
    private String KEY;
    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;
    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer ACCESS_TOKEN_MINUTE;

    public String generateToken(Authentication authentication){
String username= ((UserDetails)authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(username)
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis()+(ACCESS_TOKEN_MINUTE*60*1000)))
                .sign(Algorithm.HMAC256(KEY.getBytes()));

    }

    public DecodedJWT verifyJwt(String token) {
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        return jwtVerifier.verify(token);

    }
}
