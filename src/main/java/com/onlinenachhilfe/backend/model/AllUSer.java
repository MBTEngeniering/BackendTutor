package com.onlinenachhilfe.backend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllUSer extends User {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String emailAddress;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String password;
    private String taxId;
    private String hobbies;
    private String zoomLink;
    private String parentFirstName;
    private String parentLastName;
    private String schoolLevel;

}
