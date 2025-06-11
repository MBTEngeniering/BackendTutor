package de.fairstudy.backend.repository;

import de.fairstudy.backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostDao extends JpaRepository<Post, UUID> {
    List<Post> findBySender(UUID sender);

    List<Post> findByEmpfaenger(UUID empfaenger);
}
