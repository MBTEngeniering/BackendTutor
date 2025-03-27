package com.onlinenachhilfe.backend.dao;

import com.onlinenachhilfe.backend.model.UserRole;
import com.onlinenachhilfe.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findRoleByRole(Role role);

    void deleteRoleByRole(Role role);
}
