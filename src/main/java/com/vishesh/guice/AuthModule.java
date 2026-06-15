package com.vishesh.guice;

import com.google.inject.Inject;
import com.vishesh.auth.JWTAuthenticator;
import com.vishesh.auth.JWTAuthorizer;
import com.vishesh.auth.User;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.core.setup.Environment;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@Provider
public class AuthModule extends AuthDynamicFeature {
    @Inject
    public AuthModule(JWTAuthenticator authenticator, JWTAuthorizer authorizer, Environment environment) {
        super(new OAuthCredentialAuthFilter.Builder<User>()
                .setAuthenticator(authenticator)
                .setAuthorizer(authorizer)
                .setPrefix("Bearer")
                .buildAuthFilter());
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    }
}
