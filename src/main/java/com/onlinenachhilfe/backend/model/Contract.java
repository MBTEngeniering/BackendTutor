package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contractorId;
    private Long contracteId;
    private String contractContent;

    private Double hourlyRate;

    private Integer hoursPerMonth;
    private String contracteeStatus;
}
