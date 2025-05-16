package de.fairstudy.backend.controller;

import de.fairstudy.backend.dto.UserRoleDto;
import de.fairstudy.backend.model.enums.Role;
import de.fairstudy.backend.entity.UserRole;
import de.fairstudy.backend.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getRoleById(@PathVariable Long id) {
        Optional<UserRoleDto> userRoleDto = userRoleService.findRoleById(id)
                .map(userRole -> new UserRoleDto(userRole.getId(), userRole.getRole()));
        return userRoleDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public ResponseEntity<UserRoleDto> getRoleByRole(@RequestParam Role role) {
        Optional<UserRoleDto> userRoleDto = userRoleService.findRoleByRole(role)
                .map(userRole -> new UserRoleDto(userRole.getId(), userRole.getRole()));
        return userRoleDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UserRoleDto> createRole(@RequestBody UserRoleDto userRoleDto) {
        UserRole userRole = convertDtoToEntity(userRoleDto);
        UserRole createdRole = userRoleService.createRole(userRole.getRole());
        return ResponseEntity.ok(convertEntityToDto(createdRole));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long id) {
        userRoleService.deleteRoleById(id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRoleByRole(@RequestParam Role role) {
        userRoleService.deleteRoleByRole(role);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDto> updateRole(@PathVariable Long id, @RequestBody UserRoleDto userRoleDto) {
        try {

            UserRoleDto updatedRoleDto = Optional.of(userRoleService.updateRole(id, userRoleDto.getRole()))
                    .map(userRole -> new UserRoleDto(userRole.getId(), userRole.getRole()))
                    .orElseThrow();
            return ResponseEntity.ok(updatedRoleDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    private UserRole convertDtoToEntity(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();
        userRole.setRole(userRoleDto.getRole());
        return userRole;
    }

    private UserRoleDto convertEntityToDto(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setId(userRole.getId());
        userRoleDto.setRole(userRole.getRole());
        return userRoleDto;
    }
}
