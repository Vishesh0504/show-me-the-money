package com.vishesh.services;

import com.google.inject.Inject;
import com.vishesh.api.LoginCredentialsDTO;
import com.vishesh.auth.User;
import com.vishesh.core.StoredUser;
import com.vishesh.db.UsersDAO;
import com.vishesh.model.Role;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class AuthService {
    private final UsersDAO usersDAO;

    public User registerUser(LoginCredentialsDTO loginCreds) throws WebApplicationException {
        Optional<StoredUser> storedUser = usersDAO.findByEmail(loginCreds.getEmail());
        if(storedUser.isPresent()) {
            throw new WebApplicationException("Email already in use", Response.Status.CONFLICT);
        }

        String passwordHash = BCrypt.hashpw(loginCreds.getPassword(), BCrypt.gensalt());
        StoredUser newUser = StoredUser.of(loginCreds.getEmail(), passwordHash, Set.of(Role.USER));
        StoredUser createdUser = usersDAO.createUser(newUser);
        return toPrincipal(createdUser);
    }

    public User loginUser(LoginCredentialsDTO loginCreds) {
        StoredUser storedUser = usersDAO.findByEmail(loginCreds.getEmail())
                .orElseThrow(() -> new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED));

        if (!BCrypt.checkpw(loginCreds.getPassword(), storedUser.getPasswordHash())) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }

        return toPrincipal(storedUser);
    }

    private User toPrincipal(StoredUser storedUser) {
        return User.builder()
                .userId(String.valueOf(storedUser.getUserId()))
                .email(storedUser.getEmail())
                .roles(storedUser.getRoles())
                .build();
    }
}
