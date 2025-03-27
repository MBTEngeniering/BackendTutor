package com.onlinenachhilfe.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String salutation;
    private String title;
    private String lastName;
    private String firstName;
    private String emailAddress;
    private String password;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String phoneNumber;
    private String city;
    private String country;
    private LocalDate dateOfBirth;
    private String placeOfBirth;
    private String countryOfBirth;
    private String bankCode;
    private String iban;
    private String description;
    private String picture;
    private boolean accountActive;
    private  List<Long> tutorsIds = new ArrayList<>();
    private List<Long> userRoleIds = new ArrayList<>();
    private List<Long> schoolTutorsIds = new ArrayList<>();
    private List<Long> fachIds = new ArrayList<>();
    private List<Long> contractIds = new ArrayList<>();
    private List<Long> appointmentIds = new ArrayList<>();

}
