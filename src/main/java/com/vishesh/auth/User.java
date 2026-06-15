package com.vishesh.auth;

import com.vishesh.model.Role;
import lombok.Builder;
import lombok.Data;

import java.security.Principal;
import java.util.Set;

@Data
@Builder
public class User implements Principal {
    private String userId;
    private String email;
    private Set<Role> roles;

    @Override
    public String getName(){
        return email;
    }
}
