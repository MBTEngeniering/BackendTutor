package com.onlinenachhilfe.backend.dto;

import lombok.Data;

@Data
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
