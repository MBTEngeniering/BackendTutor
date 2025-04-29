package de.fairstudy.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private UUID id;
    private UUID ownerId;
    private String content;
    private Date creationDate;
    private String contractDuration;
    private double contractPrice;
}
