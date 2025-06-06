package de.fairstudy.backend.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvailabilityDto {
    private UUID userId;
    private String tag;
    private String monat;
    private String jahr;
    private String uhrzeit;
}
