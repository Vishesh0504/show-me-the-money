package com.vishesh.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vishesh.ShowMeTheMoneyConfiguration;
import com.vishesh.model.Role;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Singleton
public class JWTService {
    private final Algorithm algorithm;
    private final String issuer = "show-me-the-money";

    @Inject
    public JWTService(ShowMeTheMoneyConfiguration config){
        algorithm = Algorithm.HMAC256(config.getJWTSecret());
    }

    public String generateToken(User user){
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUserId())
                .withClaim("email", user.getEmail())
                .withArrayClaim("roles", user.getRoles().stream().map(Role::name).toArray(String[]::new))
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token){
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
    }
}
