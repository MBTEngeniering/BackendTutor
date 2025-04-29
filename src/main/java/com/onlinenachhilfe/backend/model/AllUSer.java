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
    @Column(columnDefinition = "TEXT")
    private String description;


}
