package de.fairstudy.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID sender;

    private UUID empfaenger;

    @ElementCollection
    @CollectionTable(name = "nachrichten", joinColumns = @JoinColumn(name = "post_id"))
    private List<Nachricht> nachrichten = new ArrayList<>();

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Nachricht {
        private String text;
        private LocalDateTime timestamp;
    }
}
