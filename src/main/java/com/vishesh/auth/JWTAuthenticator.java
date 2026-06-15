package com.vishesh.auth;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.vishesh.model.Role;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class JWTAuthenticator implements Authenticator<String, User> {
    private final JWTService jwtService;

    @Inject
    public JWTAuthenticator(JWTService jwtService) {
        this.jwtService = jwtService;
    }
    @Override
    public Optional<User> authenticate(String jwtToken){
        try{
            var decoded = jwtService.verifyToken(jwtToken);
            User user =  User.builder()
                    .userId(decoded.getSubject())
                    .email(decoded.getClaim("email").asString())
                    .roles(Arrays.stream(decoded.getClaim("roles").asArray(String.class))
                                   .map(Role::valueOf)
                                   .collect(Collectors.toSet()))
                    .build();
            return Optional.of(user);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}




