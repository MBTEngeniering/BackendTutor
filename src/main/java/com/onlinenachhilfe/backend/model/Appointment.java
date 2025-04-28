package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
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
