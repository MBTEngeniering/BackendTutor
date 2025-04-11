package com.onlinenachhilfe.backend.dto;

import com.onlinenachhilfe.backend.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FachDto {
    private Long id;
    private Subject fach;
    private String translation;
}
