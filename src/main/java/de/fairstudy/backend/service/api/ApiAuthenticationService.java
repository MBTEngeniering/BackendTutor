package de.fairstudy.backend.service.api;

import de.fairstudy.backend.auth.AuthenticationResponse;
import de.fairstudy.backend.dto.LoginRequestDto;
import de.fairstudy.backend.entity.AllUser;
import de.fairstudy.backend.repository.AllUserDao;
import de.fairstudy.backend.service.AllUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApiAuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AllUserService allUserService;
    private final AllUserDao allUserDao;

    /*
    public AuthenticationResponse register(RegisterRequest request) {
        var userDto = new UserDto();
        userDto.setFirstName(request.getFirstname());
        userDto.setLastName(request.getLastname());
        userDto.setEmail(request.getEmail());
        userDto.setPassword(request.getPassword());

        if (!UserRole.contains(request.getRole())) {
            throw new EnumConstantNotPresentException(UserRole.class, request.getRole());
        }

        userDto.setRole(UserRole.valueOf(request.getRole()));

        userService.saveUser(userDto);
        var jwtToken = jwtService.generateToken(userDto);
        var time = jwtService.expirationTimeAsString(jwtToken);
        return AuthenticationResponse.builder().token(jwtToken).expirationTime(time).build();
    }
     */

    /* Backeup code for authentication
    public AuthenticationResponse authenticate(LoginRequestDto request) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        AllUser user;
        String jwtToken;
        String time;
        try {
            user = allUserService.login(request.getEmailOrPhoneNumber(), request.getPassword());
            jwtToken = jwtService.generateToken(allUserService.convertToDto(user));
            time = jwtService.expirationTimeAsString(jwtToken);
            user.setLastLogin(LocalDateTime.now());
            allUserDao.save(user);
        } catch (Exception e) {
            throw e;
        }

        return AuthenticationResponse.builder().token(jwtToken).expirationTime(time).username(user.getEmailAddress()).build();
    }
     */

    public AuthenticationResponse authenticate(LoginRequestDto request) {
        AllUser user;
        String jwtToken;
        String time;
        ResponseCookie cookie = null;
        user = allUserService.login(request.getEmailOrPhoneNumber(), request.getPassword());

        jwtToken = jwtService.generateToken(allUserService.convertToDto(user));

        time = jwtService.expirationTimeAsString(jwtToken);
        var timestamp = jwtService.expirationDate().getTime();

        user.setLastLogin(LocalDateTime.now());
        allUserDao.save(user);

        cookie = jwtService.generateJwtCookie(jwtToken);

        return AuthenticationResponse.builder().token(jwtToken).expirationTime(time).username(user.getEmailAddress()).cookie(cookie.toString()).user(user).timestamp(timestamp).build();
    }

    public AuthenticationResponse logout() {
        var cookie = ResponseCookie.from("jwt", "")
                .httpOnly(true) // Prevent XSS
                .secure(false)   // Send only over HTTPS
                .path("/")      // Accessible across all paths
                .maxAge(0) // Cookie expiration (seconds)
                .sameSite("Lax") // CSRF protection
                .build();

        return AuthenticationResponse.builder().cookie(cookie.toString()).build();
    }
}