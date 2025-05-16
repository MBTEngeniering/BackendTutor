package de.fairstudy.backend.dto;

import de.fairstudy.backend.model.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FachDto {
    private Long id;
    private Subject fach;
    private String en;
    private String de;
    private String fr;
    private String translation;
}
