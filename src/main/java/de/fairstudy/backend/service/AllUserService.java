package de.fairstudy.backend.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.fairstudy.backend.Utils.UUIDGenerator;
import de.fairstudy.backend.dao.AllUserDao;
import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.exception.InvalidCredentialsException;
import de.fairstudy.backend.exception.UserAlreadyExistsException;
import de.fairstudy.backend.exception.UserNotFoundException;
import de.fairstudy.backend.model.AllUSer;
import de.fairstudy.backend.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AllUserService {


    private final ContractService contractService;
    private AllUserDao allUserDao;
    private PasswordEncryptionUtil passwordEncryptionUtil;
    private AppointmentService appointmentService;
@Autowired
    public AllUserService(AllUserDao allUserDao, PasswordEncryptionUtil passwordEncryptionUtil, ContractService contractService) {
        this.allUserDao = allUserDao;
        this.passwordEncryptionUtil = passwordEncryptionUtil;
    this.contractService = contractService;
}

    public AllUserDto createUsers(AllUserDto allUserDto) {
        // Überprüfe, ob die E-Mail oder Telefonnummer bereits existiert
        if (allUserDao.findByEmailAddress(allUserDto.getEmailAddress()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser E-Mail-Adresse existiert bereits.");
        }

        if (allUserDao.findByPhoneNumber(allUserDto.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser Telefonnummer existiert bereits.");
        }

        UUID uid= new UUIDGenerator().generateUUID();
        allUserDto.setId(uid);
        AllUSer allUSer = convertToEntity(allUserDto);
        AllUSer savedTutor = allUserDao.save(allUSer);
        return convertToDto(savedTutor);
    }

    public List<AllUserDto> getAllUsers() {
        return allUserDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUsersById(UUID id) {
        return allUserDao.findById(id).map(this::convertToDto);
    }

    public Optional<AllUserDto> getUsersByEmail(String email) {
        return allUserDao.findByEmailAddress(email).map(this::convertToDto);
    }

    public void deleteTutor(UUID id) {
        allUserDao.deleteById(id);
    }

    public List<AllUserDto> getUsersByLastName(String lastName) {
        return allUserDao.findByLastName(lastName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AllUserDto> getUserByFirstName(String firstName) {
        return allUserDao.findByFirstName(firstName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AllUserDto> getUserByAddress(String street, String houseNumber, String postalCode) {
        return allUserDao.findByStreetAndHouseNumberAndPostalCode(street, houseNumber, postalCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUsersByPhoneNumber(String phoneNumber) {
        return allUserDao.findByPhoneNumber(phoneNumber).map(this::convertToDto);
    }

    public List<AllUserDto> getUserByDateOfBirth(LocalDate dateOfBirth) {
        return allUserDao.findByDateOfBirth(dateOfBirth).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUserByIban(String iban) {
        return allUserDao.findByIban(iban).map(this::convertToDto);
    }

    public List<AllUserDto> getUserByTaxId(String taxId) {
        return allUserDao.findByTaxId(taxId).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public AllUserDto updateUsers(UUID id, AllUserDto allUserDto) {

        Optional<AllUSer> optionalAllUSer = allUserDao.findById(id);
        if (optionalAllUSer.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        AllUSer existingUser =convertToEntity(allUserDto);
        AllUSer updatedTutor = allUserDao.save(existingUser);

        return convertToDto(updatedTutor);
    }
    public void deleteUsersByEmail(String email) {
        AllUSer allUSer = allUserDao.findByEmailAddress(email).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Email " + email + " not found."));
        List<UUID> contractIds = allUSer.getContractIds();
        List<UUID> appointmentIds = allUSer.getAppointmentIds();
        for(UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for(UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }
        allUserDao.delete(allUSer);
    }
    public void deleteUsersByPhoneNumber(String phoneNumber) {
        AllUSer tutor = allUserDao.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Phone Number " + phoneNumber + " not found."));
        List<UUID> contractIds = tutor.getContractIds();
        List<UUID> appointmentIds = tutor.getAppointmentIds();
        for(UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for(UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }
        allUserDao.delete(tutor);
    }
    public void deleteUsersById(UUID id) {

        AllUSer allUSer = allUserDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Email " + id + " not found."));

         List<UUID> tutorsIds = allUSer.getTutorsIds();
       List<UUID> schoolTutorsIds = allUSer.getSchoolTutorsIds();

        List<UUID> contractIds = allUSer.getContractIds();
        List<UUID> appointmentIds = allUSer.getAppointmentIds();
        for(UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for(UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }

        for(UUID tutorId : tutorsIds) {
            AllUSer tutor = allUserDao.findById(tutorId).orElseThrow(() ->
                    new IllegalArgumentException("Tutor with Phone Number " + tutorId + " not found."));
            tutor.getSchoolTutorsIds().remove(tutorId);
            allUserDao.save(tutor);
        }
        for(UUID schoolId : schoolTutorsIds) {
            AllUSer student = allUserDao.findById(schoolId).orElseThrow(() ->
                    new IllegalArgumentException("Tutor with Phone Number " + schoolId + " not found."));
            student.getTutorsIds().remove(schoolId);
            allUserDao.save(student);
        }


        allUserDao.deleteById(id);
    }


    private AllUSer convertToEntity(AllUserDto allUserDto) {
        AllUSer allUSer = new AllUSer();
        allUSer.setSalutation(allUserDto.getSalutation());
        allUSer.setTitle(allUserDto.getTitle());
        allUSer.setLastName(allUserDto.getLastName());
        allUSer.setFirstName(allUserDto.getFirstName());
        allUSer.setEmailAddress(allUserDto.getEmailAddress());
        allUSer.setPassword(passwordEncryptionUtil.encrypt(allUserDto.getPassword()));
        allUSer.setStreet(allUserDto.getStreet());
        allUSer.setHouseNumber(allUserDto.getHouseNumber());
        allUSer.setPostalCode(allUserDto.getPostalCode());
        allUSer.setPhoneNumber(allUserDto.getPhoneNumber());
        allUSer.setCity(allUserDto.getCity());
        allUSer.setCountry(allUserDto.getCountry());
        allUSer.setDateOfBirth(allUserDto.getDateOfBirth());
        allUSer.setPlaceOfBirth(allUserDto.getPlaceOfBirth());
        allUSer.setCountryOfBirth(allUserDto.getCountryOfBirth());
        allUSer.setBankCode(allUserDto.getBankCode());
        allUSer.setIban(allUserDto.getIban());
        allUSer.setDescription(allUserDto.getDescription());
        allUSer.setPicture(allUserDto.getPicture());

        allUSer.setUserRoleIds(allUserDto.getUserRoleIds());
        allUSer.setTutorsIds(allUserDto.getTutorsIds());
        allUSer.setAppointmentIds(allUserDto.getAppointmentIds());
        allUSer.setFachIds(allUserDto.getFachIds());
        allUSer.setContractIds(allUserDto.getContractIds());
        allUSer.setSchoolTutorsIds(allUserDto.getSchoolTutorsIds());

        allUSer.setAccountActive(allUserDto.isAccountActive());
        allUSer.setTaxId(allUserDto.getTaxId());
        allUSer.setHobbies(allUserDto.getHobbies());
        allUSer.setZoomLink(allUserDto.getZoomLink());
       allUSer.setAvailabilityTime(allUserDto.getAvailabilityTime());
        allUSer.setParentFirstName(allUserDto.getParentFirstName());
        allUSer.setParentLastName(allUserDto.getParentLastName());
        allUSer.setSchoolLevel(allUserDto.getSchoolLevel());

        return allUSer;
    }

    private AllUserDto convertToDto(AllUSer alleUser) {
        AllUserDto allUserDto = new AllUserDto();
        allUserDto.setSalutation(alleUser.getSalutation());
        allUserDto.setTitle(alleUser.getTitle());
        allUserDto.setLastName(alleUser.getLastName());
        allUserDto.setFirstName(alleUser.getFirstName());
        allUserDto.setEmailAddress(alleUser.getEmailAddress());
        allUserDto.setPassword(passwordEncryptionUtil.decrypt(alleUser.getPassword()));
        allUserDto.setStreet(alleUser.getStreet());
        allUserDto.setHouseNumber(alleUser.getHouseNumber());
        allUserDto.setPostalCode(alleUser.getPostalCode());
        allUserDto.setPhoneNumber(alleUser.getPhoneNumber());
        allUserDto.setCity(alleUser.getCity());
        allUserDto.setCountry(alleUser.getCountry());
        allUserDto.setDateOfBirth(alleUser.getDateOfBirth());
        allUserDto.setPlaceOfBirth(alleUser.getPlaceOfBirth());
        allUserDto.setCountryOfBirth(alleUser.getCountryOfBirth());
        allUserDto.setBankCode(alleUser.getBankCode());
        allUserDto.setIban(alleUser.getIban());
        allUserDto.setDescription(alleUser.getDescription());
        allUserDto.setPicture(alleUser.getPicture());
        allUserDto.setUserRoleIds(alleUser.getUserRoleIds());
        allUserDto.setTutorsIds(alleUser.getTutorsIds());
        allUserDto.setAppointmentIds(alleUser.getAppointmentIds());
        allUserDto.setFachIds(alleUser.getFachIds());
        allUserDto.setContractIds(alleUser.getContractIds());
        allUserDto.setSchoolTutorsIds(alleUser.getSchoolTutorsIds());
        allUserDto.setAvailabilityTime(alleUser.getAvailabilityTime());

        allUserDto.setAccountActive(alleUser.isAccountActive());
        allUserDto.setTaxId(alleUser.getTaxId());
        allUserDto.setHobbies(alleUser.getHobbies());
        allUserDto.setZoomLink(alleUser.getZoomLink());

        allUserDto.setParentFirstName(alleUser.getParentFirstName());
        allUserDto.setParentLastName(alleUser.getParentLastName());
        allUserDto.setSchoolLevel(alleUser.getSchoolLevel());


        return allUserDto;
    }
    public AllUSer login(String emailOrPhoneNumber, String password) {

        Optional<AllUSer> allUSerOptional;

        if (emailOrPhoneNumber.contains("@")) {
            allUSerOptional = allUserDao.findByEmailAddress(emailOrPhoneNumber);
        } else {
            allUSerOptional = allUserDao.findByPhoneNumber(emailOrPhoneNumber);
        }

        if (allUSerOptional.isEmpty()) {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }

        AllUSer allUSer = allUSerOptional.get();

        if (password.equals(passwordEncryptionUtil.decrypt(allUSer.getPassword()))) {
            return allUSer;
        } else {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }
    }
    public List<AllUserDto> createUsersIgnoreErrors(List<AllUserDto> allUserDtoList) {
        return allUserDtoList.stream()
                .map(allUserDto -> {
                    try {
                        return createUsers(allUserDto);
                    } catch (UserAlreadyExistsException e) {
                        // Optional: Logging
                        System.out.println("Fehler beim Erstellen von Tutor: " + e.getMessage());
                        return null;
                    }
                })
                .filter(allUserDto -> allUserDto != null)
                .collect(Collectors.toList());
    }


}
