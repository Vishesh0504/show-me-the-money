package com.vishesh.auth;

import com.google.inject.Singleton;
import com.vishesh.model.Role;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.logback.shaded.checkerframework.checker.nullness.qual.Nullable;
import jakarta.ws.rs.container.ContainerRequestContext;

@Singleton
public class JWTAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role, @Nullable ContainerRequestContext requestContext) {
        try {
            Role requiredRole = Role.valueOf(role);
            return user.getRoles() != null && user.getRoles().contains(requiredRole);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}