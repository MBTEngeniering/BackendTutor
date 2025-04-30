package de.fairstudy.backend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDto {

    private String emailOrPhoneNumber;
    private String password;
}
