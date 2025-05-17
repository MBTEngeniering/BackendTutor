package de.fairstudy.backend.controller;

import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final AllUserService allUserService;

    public StatusController(AllUserService allUserService) {
        this.allUserService = allUserService;
    }

    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    @GetMapping("/students")
    public List<AllUserDto> getStudents() {
        return filterUsersByRole(1L);
    }

    @Secured({"ROLE_STUDENT", "ROLE_ADMIN"})
    @GetMapping("/tutors")
    public List<AllUserDto> getTutors() {
        return filterUsersByRole(2L);
    }

    @GetMapping("/entreprise")
    public List<AllUserDto> getEntreprises() {
        return filterUsersByRole(3L);
    }

    @GetMapping("/admins")
    public List<AllUserDto> getAdmins() {
        return filterUsersByRole(5L);
    }

    @GetMapping("/support")
    public List<AllUserDto> getSupporters() {
        return filterUsersByRole(6L);
    }

    @GetMapping("/finance")
    public List<AllUserDto> getFinanceTeam() {
        return filterUsersByRole(4L);
    }

    private List<AllUserDto> filterUsersByRole(Long userRoleId) {
        List<AllUserDto> allUsers = allUserService.getAllUsers();
        return allUsers.stream()
                .filter(user -> user.getUserRoleIds() != null &&
                        user.getUserRoleIds().contains(userRoleId))
                .collect(Collectors.toList());
    }
}
