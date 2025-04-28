package de.fairstudy.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
