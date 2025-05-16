package de.fairstudy.backend.auth;

import de.fairstudy.backend.entity.AllUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;

    private String expirationTime;

    private String username;

    private String cookie;

    private AllUser user;
}
