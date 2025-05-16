package de.fairstudy.backend.dto;

import de.fairstudy.backend.model.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {
    private Long id;
    private Role role;
}
