package com.vishesh.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class AuthTokenResponseDTO {
    @JsonProperty
    String token;
}

