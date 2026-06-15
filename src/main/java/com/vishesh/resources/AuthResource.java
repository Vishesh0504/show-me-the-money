package com.vishesh.resources;

import com.google.inject.Inject;
import com.vishesh.api.AuthTokenResponseDTO;
import com.vishesh.api.LoginCredentialsDTO;
import com.vishesh.auth.JWTService;
import com.vishesh.auth.User;
import com.vishesh.services.AuthService;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class AuthResource {
    private final AuthService authService;
    private final JWTService jwtService;

    @Path("/login")
    @POST
    @UnitOfWork
    public Response login(@Valid LoginCredentialsDTO loginCredentials) {
        User user = authService.loginUser(loginCredentials);
        String token = jwtService.generateToken(user);
        return Response.ok(new AuthTokenResponseDTO(token)).build();
    }

    @Path("/signup")
    @POST
    @UnitOfWork
    public Response signup(@Valid LoginCredentialsDTO loginCredentials) {
        User user = authService.registerUser(loginCredentials);
        String token = jwtService.generateToken(user);
        return Response.status(Response.Status.CREATED)
                .entity(new AuthTokenResponseDTO(token))
                .build();
    }

}
