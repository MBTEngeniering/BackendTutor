package com.onlinenachhilfe.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseDto {

    private Long id;
    private String companyName;
    private String registrationNumber;
    private String taxNumber;
    private String website;
    private String contactPerson;
    private String emailAddress;
    private String phoneNumber;
    private String address;
    private String description;
}
