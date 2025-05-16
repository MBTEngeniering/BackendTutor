package de.fairstudy.backend.service;

import de.fairstudy.backend.repository.UserRoleDao;
import de.fairstudy.backend.entity.UserRole;
import de.fairstudy.backend.model.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleService {

    private final UserRoleDao userRoleDao;

    @Autowired
    public UserRoleService(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    // Rolle nach ID suchen
    public Optional<UserRole> findRoleById(Long id) {
        return userRoleDao.findById(id);
    }

    // Rolle nach Rolle suchen
    public Optional<UserRole> findRoleByRole(Role role) {
        return userRoleDao.findRoleByRole(role);
    }

    // Neue Rolle erstellen
    public UserRole createRole(Role role) {
        UserRole userRole = new UserRole();
        userRole.setRole(role);
        return userRoleDao.save(userRole);
    }

    // Rolle nach ID löschen
    public void deleteRoleById(Long id) {
        userRoleDao.deleteById(id);
    }

    // Rolle nach Rolle löschen
    public void deleteRoleByRole(Role role) {
        userRoleDao.deleteRoleByRole(role);
    }

    // Rolle aktualisieren
    public UserRole updateRole(Long id, Role newRole) {
        Optional<UserRole> optionalUserRole = userRoleDao.findById(id);
        if (optionalUserRole.isPresent()) {
            UserRole userRole = optionalUserRole.get();
            userRole.setRole(newRole);
            return userRoleDao.save(userRole);
        } else {
            throw new IllegalArgumentException("Role with ID " + id + " not found");
        }
    }
}
