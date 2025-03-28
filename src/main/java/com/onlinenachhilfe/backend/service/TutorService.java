package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.TutorDao;
import com.onlinenachhilfe.backend.dto.TutorDto;
import com.onlinenachhilfe.backend.exception.UserAlreadyExistsException;
import com.onlinenachhilfe.backend.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorService {

    @Autowired
    private TutorDao tutorDao;

    public TutorDto createTutor(TutorDto tutorDto) {
        // Überprüfe, ob die E-Mail oder Telefonnummer bereits existiert
        if (tutorDao.findByEmailAddress(tutorDto.getEmailAddress()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser E-Mail-Adresse existiert bereits.");
        }

        if (tutorDao.findByPhoneNumber(tutorDto.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser Telefonnummer existiert bereits.");
        }
        Tutor tutor = convertToEntity(tutorDto);
        Tutor savedTutor = tutorDao.save(tutor);
        return convertToDto(savedTutor);
    }

    public List<TutorDto> getAllTutors() {
        return tutorDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<TutorDto> getTutorById(Long id) {
        return tutorDao.findById(id).map(this::convertToDto);
    }

    public Optional<TutorDto> getTutorByEmail(String email) {
        return tutorDao.findByEmailAddress(email).map(this::convertToDto);
    }

    public void deleteTutor(Long id) {
        tutorDao.deleteById(id);
    }

    public List<TutorDto> getTutorsByLastName(String lastName) {
        return tutorDao.findByLastName(lastName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<TutorDto> getTutorsByFirstName(String firstName) {
        return tutorDao.findByFirstName(firstName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<TutorDto> getTutorsByAddress(String street, String houseNumber, String postalCode) {
        return tutorDao.findByStreetAndHouseNumberAndPostalCode(street, houseNumber, postalCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<TutorDto> getTutorByPhoneNumber(String phoneNumber) {
        return tutorDao.findByPhoneNumber(phoneNumber).map(this::convertToDto);
    }

    public List<TutorDto> getTutorsByDateOfBirth(LocalDate dateOfBirth) {
        return tutorDao.findByDateOfBirth(dateOfBirth).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<TutorDto> getTutorByIban(String iban) {
        return tutorDao.findByIban(iban).map(this::convertToDto);
    }

    public List<TutorDto> getTutorsByTaxId(String taxId) {
        return tutorDao.findByTaxId(taxId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private Tutor convertToEntity(TutorDto tutorDto) {
        Tutor tutor = new Tutor();
        tutor.setSalutation(tutorDto.getSalutation());
        tutor.setTitle(tutorDto.getTitle());
        tutor.setLastName(tutorDto.getLastName());
        tutor.setFirstName(tutorDto.getFirstName());
        tutor.setEmailAddress(tutorDto.getEmailAddress());
        tutor.setPassword(tutorDto.getPassword());
        tutor.setStreet(tutorDto.getStreet());
        tutor.setHouseNumber(tutorDto.getHouseNumber());
        tutor.setPostalCode(tutorDto.getPostalCode());
        tutor.setPhoneNumber(tutorDto.getPhoneNumber());
        tutor.setCity(tutorDto.getCity());
        tutor.setCountry(tutorDto.getCountry());
        tutor.setDateOfBirth(tutorDto.getDateOfBirth());
        tutor.setPlaceOfBirth(tutorDto.getPlaceOfBirth());
        tutor.setCountryOfBirth(tutorDto.getCountryOfBirth());
        tutor.setBankCode(tutorDto.getBankCode());
        tutor.setIban(tutorDto.getIban());
        tutor.setDescription(tutorDto.getDescription());
        tutor.setPicture(tutorDto.getPicture());

        tutor.setUserRoleIds(tutorDto.getUserRoleIds());
        tutor.setTutorsIds(tutorDto.getTutorsIds());
        tutor.setAppointmentIds(tutorDto.getAppointmentIds());
        tutor.setFachIds(tutorDto.getFachIds());
        tutor.setContractIds(tutorDto.getContractIds());
        tutor.setSchoolTutorsIds(tutorDto.getSchoolTutorsIds());

        tutor.setAccountActive(tutorDto.isAccountActive());
        tutor.setTaxId(tutorDto.getTaxId());
        tutor.setHobbies(tutorDto.getHobbies());

        return tutor;
    }

    private TutorDto convertToDto(Tutor tutor) {
        TutorDto tutorDto = new TutorDto();
        tutorDto.setSalutation(tutor.getSalutation());
        tutorDto.setTitle(tutor.getTitle());
        tutorDto.setLastName(tutor.getLastName());
        tutorDto.setFirstName(tutor.getFirstName());
        tutorDto.setEmailAddress(tutor.getEmailAddress());
        tutorDto.setPassword(tutor.getPassword());
        tutorDto.setStreet(tutor.getStreet());
        tutorDto.setHouseNumber(tutor.getHouseNumber());
        tutorDto.setPostalCode(tutor.getPostalCode());
        tutorDto.setPhoneNumber(tutor.getPhoneNumber());
        tutorDto.setCity(tutor.getCity());
        tutorDto.setCountry(tutor.getCountry());
        tutorDto.setDateOfBirth(tutor.getDateOfBirth());
        tutorDto.setPlaceOfBirth(tutor.getPlaceOfBirth());
        tutorDto.setCountryOfBirth(tutor.getCountryOfBirth());
        tutorDto.setBankCode(tutor.getBankCode());
        tutorDto.setIban(tutor.getIban());
        tutorDto.setDescription(tutor.getDescription());
        tutorDto.setPicture(tutor.getPicture());
        tutorDto.setUserRoleIds(tutor.getUserRoleIds());
        tutorDto.setTutorsIds(tutor.getTutorsIds());
        tutorDto.setAppointmentIds(tutor.getAppointmentIds());
        tutorDto.setFachIds(tutor.getFachIds());
        tutorDto.setContractIds(tutor.getContractIds());
        tutorDto.setSchoolTutorsIds(tutor.getSchoolTutorsIds());

        tutorDto.setAccountActive(tutor.isAccountActive());
        tutorDto.setTaxId(tutor.getTaxId());
        tutorDto.setHobbies(tutor.getHobbies());

        return tutorDto;
    }
}
