package com.onlinenachhilfe.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String registrationNumber;
    private String taxNumber;
    private String website;
    private String contactPerson;
    @Column(unique = true, nullable = false)
    private String emailAddress;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    private String description;
}
