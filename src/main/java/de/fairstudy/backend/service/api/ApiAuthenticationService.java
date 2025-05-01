package de.fairstudy.backend.service.api;

import de.fairstudy.backend.auth.AuthenticationRequest;
import de.fairstudy.backend.auth.AuthenticationResponse;
import de.fairstudy.backend.model.AllUser;
import de.fairstudy.backend.service.AllUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApiAuthenticationService {
    //@TODO: Remove repo, use userService instead
//    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
//    private final UserService userService;

    private final AllUserService allUserService;


    /*
    public ApiAuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }
     */

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
/*
    public AuthenticationResponse authenticate(de.fairstudy.backend.auth.AuthenticationRequest request) {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        AllUSer user;
        try {
            user = allUserService.login(request.getEmail(), request.getPassword());
        } catch (Exception e) {
            throw e;
        }

        var jwtToken = jwtService.generateToken(allUserService.convertToDto(user));
        var time = jwtService.expirationTimeAsString(jwtToken);
//        user.setLastLogin(LocalDateTime.now());
//        userRepository.save(user);
        return AuthenticationResponse.builder().token(jwtToken).expirationTime(time).username(user.getEmailAddress()).build();
    }*/
}