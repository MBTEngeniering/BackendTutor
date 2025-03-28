package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private Long id;
    private Long contractorId;
    private Long contracteId;
    private String contractContent;
    private Double hourlyRate;
    private Integer hoursPerMonth;
    private String contracteeStatus;
}
