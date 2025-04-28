package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private UUID id;
    private String startDate;
    private String endDate;
    private UUID tutorId;
    private UUID studentId;
    private Boolean studentIsPresent;
    private Boolean tutorIsPresent;
    private Long fachId;
    private double hourlyRate;
}
