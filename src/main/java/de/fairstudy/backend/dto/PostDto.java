package de.fairstudy.backend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private UUID sender;
    private UUID empfaenger;
    private List<NachrichtDto> nachrichten;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class NachrichtDto {
        private String text;
        private LocalDateTime timestamp;
    }
}
