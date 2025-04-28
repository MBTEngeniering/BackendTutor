package de.fairstudy.backend.dao;

import de.fairstudy.backend.model.UserRole;
import de.fairstudy.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findRoleByRole(Role role);

    void deleteRoleByRole(Role role);
}
