package com.vishesh.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginCredentialsDTO {

    @NotBlank
    @Email
    @JsonProperty
    private String email;

    @NotBlank
    @JsonProperty
    private String password;
}

