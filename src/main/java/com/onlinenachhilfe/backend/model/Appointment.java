package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
