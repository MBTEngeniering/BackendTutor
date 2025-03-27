package com.onlinenachhilfe.backend.dto;

import com.onlinenachhilfe.backend.model.Role;
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
