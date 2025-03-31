package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private Long tutorId;
    private Long studentId;
    private Boolean studentIsPresent;
    private Boolean tutorIsPresent;
    private Long fachId;
    private double hourlyRate;
}
