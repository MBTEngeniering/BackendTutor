package de.fairstudy.backend.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private UUID id;
    private UUID ownerId;
    private String content;
    private Date creationDate;
    private String contractDuration;
    private double contractPrice;
}
