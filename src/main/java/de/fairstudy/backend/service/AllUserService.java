package de.fairstudy.backend.service;

import de.fairstudy.backend.Utils.UUIDGenerator;
import de.fairstudy.backend.repository.AllUserDao;
import de.fairstudy.backend.dto.AllUserDto;
import de.fairstudy.backend.exception.InvalidCredentialsException;
import de.fairstudy.backend.exception.UserAlreadyExistsException;
import de.fairstudy.backend.exception.UserNotFoundException;
import de.fairstudy.backend.entity.AllUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AllUserService {

    private final ContractService contractService;
    private final AllUserDao AllUserDao;
    private final PasswordEncryptionUtil passwordEncryptionUtil;
    private final AppointmentService appointmentService;

    public AllUserService(AllUserDao AllUserDao, PasswordEncryptionUtil passwordEncryptionUtil, ContractService contractService, AppointmentService appointmentService) {
        this.AllUserDao = AllUserDao;
        this.passwordEncryptionUtil = passwordEncryptionUtil;
        this.contractService = contractService;
        this.appointmentService = appointmentService;
    }

    public AllUserDto createUsers(AllUserDto AllUserDto) {
        // Überprüfe, ob die E-Mail oder Telefonnummer bereits existiert
        if (AllUserDao.findByEmailAddress(AllUserDto.getEmailAddress()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser E-Mail-Adresse existiert bereits.");
        }

        if (AllUserDao.findByPhoneNumber(AllUserDto.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Tutor mit dieser Telefonnummer existiert bereits.");
        }

        UUID uid = new UUIDGenerator().generateUUID();
        AllUserDto.setId(uid);
        AllUser AllUser = convertToEntity(AllUserDto);
        AllUser savedTutor = AllUserDao.save(AllUser);
        return convertToDto(savedTutor);
    }

    public List<AllUserDto> getAllUsers() {
        return AllUserDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUsersById(UUID id) {
        return AllUserDao.findById(id).map(this::convertToDto);
    }

    public Optional<AllUserDto> getUsersByEmail(String email) {
        return AllUserDao.findByEmailAddress(email).map(this::convertToDto);
    }

    public void deleteTutor(UUID id) {
        AllUserDao.deleteById(id);
    }

    public List<AllUserDto> getUsersByLastName(String lastName) {
        return AllUserDao.findByLastName(lastName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AllUserDto> getUserByFirstName(String firstName) {
        return AllUserDao.findByFirstName(firstName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<AllUserDto> getUserByAddress(String street, String houseNumber, String postalCode) {
        return AllUserDao.findByStreetAndHouseNumberAndPostalCode(street, houseNumber, postalCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUsersByPhoneNumber(String phoneNumber) {
        return AllUserDao.findByPhoneNumber(phoneNumber).map(this::convertToDto);
    }

    public List<AllUserDto> getUserByDateOfBirth(LocalDate dateOfBirth) {
        return AllUserDao.findByDateOfBirth(dateOfBirth).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<AllUserDto> getUserByIban(String iban) {
        return AllUserDao.findByIban(iban).map(this::convertToDto);
    }

    public List<AllUserDto> getUserByTaxId(String taxId) {
        return AllUserDao.findByTaxId(taxId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AllUserDto updateUsers(UUID id, AllUserDto AllUserDto) {

        Optional<AllUser> optionalAllUser = AllUserDao.findById(id);
        if (optionalAllUser.isEmpty()) {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        AllUser existingUser =convertToEntity(AllUserDto);
        existingUser.setId(optionalAllUser.get().getId());
        AllUser updatedTutor = AllUserDao.save(existingUser);

        return convertToDto(updatedTutor);
    }

    public void deleteUsersByEmail(String email) {
        AllUser AllUser = AllUserDao.findByEmailAddress(email).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Email " + email + " not found."));
        List<UUID> contractIds = AllUser.getContractIds();
        List<UUID> appointmentIds = AllUser.getAppointmentIds();
        for (UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for (UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }
        AllUserDao.delete(AllUser);
    }

    public void deleteUsersByPhoneNumber(String phoneNumber) {
        AllUser tutor = AllUserDao.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Phone Number " + phoneNumber + " not found."));
        List<UUID> contractIds = tutor.getContractIds();
        List<UUID> appointmentIds = tutor.getAppointmentIds();
        for (UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for (UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }
        AllUserDao.delete(tutor);
    }

    public void deleteUsersById(UUID id) {

        AllUser AllUser = AllUserDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Tutor with Email " + id + " not found."));

        List<UUID> tutorsIds = AllUser.getTutorsIds();
        List<UUID> schoolTutorsIds = AllUser.getSchoolTutorsIds();

        List<UUID> contractIds = AllUser.getContractIds();
        List<UUID> appointmentIds = AllUser.getAppointmentIds();
        for (UUID contractId : contractIds) {
            contractService.deleteContract(contractId);
        }
        for (UUID appointmentId : appointmentIds) {
            appointmentService.deleteAppointment(appointmentId);
        }

        for (UUID tutorId : tutorsIds) {
            AllUser tutor = AllUserDao.findById(tutorId).orElseThrow(() ->
                    new IllegalArgumentException("Tutor with Phone Number " + tutorId + " not found."));
            tutor.getSchoolTutorsIds().remove(tutorId);
            AllUserDao.save(tutor);
        }
        for (UUID schoolId : schoolTutorsIds) {
            AllUser student = AllUserDao.findById(schoolId).orElseThrow(() ->
                    new IllegalArgumentException("Tutor with Phone Number " + schoolId + " not found."));
            student.getTutorsIds().remove(schoolId);
            AllUserDao.save(student);
        }


        AllUserDao.deleteById(id);
    }


    private AllUser convertToEntity(AllUserDto AllUserDto) {
        AllUser AllUser = new AllUser();
        AllUser.setSalutation(AllUserDto.getSalutation());
        AllUser.setTitle(AllUserDto.getTitle());
        AllUser.setLastName(AllUserDto.getLastName());
        AllUser.setFirstName(AllUserDto.getFirstName());
        AllUser.setEmailAddress(AllUserDto.getEmailAddress());
        AllUser.setPassword(passwordEncryptionUtil.encrypt(AllUserDto.getPassword()));
        AllUser.setStreet(AllUserDto.getStreet());
        AllUser.setHouseNumber(AllUserDto.getHouseNumber());
        AllUser.setPostalCode(AllUserDto.getPostalCode());
        AllUser.setPhoneNumber(AllUserDto.getPhoneNumber());
        AllUser.setCity(AllUserDto.getCity());
        AllUser.setCountry(AllUserDto.getCountry());
        AllUser.setDateOfBirth(AllUserDto.getDateOfBirth());
        AllUser.setPlaceOfBirth(AllUserDto.getPlaceOfBirth());
        AllUser.setCountryOfBirth(AllUserDto.getCountryOfBirth());
        AllUser.setBankCode(AllUserDto.getBankCode());
        AllUser.setIban(AllUserDto.getIban());
        AllUser.setDescription(AllUserDto.getDescription());
        AllUser.setPicture(AllUserDto.getPicture());

        //@TODO: Dangerous!
        AllUser.setUserRoleIds(AllUserDto.getUserRoleIds());
        AllUser.setTutorsIds(AllUserDto.getTutorsIds());
        AllUser.setAppointmentIds(AllUserDto.getAppointmentIds());

        AllUser.setFachIds(AllUserDto.getFachIds());
        AllUser.setContractIds(AllUserDto.getContractIds());
        AllUser.setSchoolTutorsIds(AllUserDto.getSchoolTutorsIds());
        AllUser.setAccountActive(AllUserDto.isAccountActive());
        AllUser.setTaxId(AllUserDto.getTaxId());
        AllUser.setHobbies(AllUserDto.getHobbies());
        AllUser.setZoomLink(AllUserDto.getZoomLink());
        AllUser.setAvailabilityTime(AllUserDto.getAvailabilityTime());
        AllUser.setParentFirstName(AllUserDto.getParentFirstName());
        AllUser.setParentLastName(AllUserDto.getParentLastName());
        AllUser.setSchoolLevel(AllUserDto.getSchoolLevel());

        return AllUser;
    }

    public AllUserDto convertToDto(AllUser alleUser) {
        AllUserDto AllUserDto = new AllUserDto();
        AllUserDto.setSalutation(alleUser.getSalutation());
        AllUserDto.setTitle(alleUser.getTitle());
        AllUserDto.setLastName(alleUser.getLastName());
        AllUserDto.setFirstName(alleUser.getFirstName());
        AllUserDto.setEmailAddress(alleUser.getEmailAddress());
        AllUserDto.setPassword(passwordEncryptionUtil.decrypt(alleUser.getPassword()));
        AllUserDto.setStreet(alleUser.getStreet());
        AllUserDto.setHouseNumber(alleUser.getHouseNumber());
        AllUserDto.setPostalCode(alleUser.getPostalCode());
        AllUserDto.setPhoneNumber(alleUser.getPhoneNumber());
        AllUserDto.setCity(alleUser.getCity());
        AllUserDto.setCountry(alleUser.getCountry());
        AllUserDto.setDateOfBirth(alleUser.getDateOfBirth());
        AllUserDto.setPlaceOfBirth(alleUser.getPlaceOfBirth());
        AllUserDto.setCountryOfBirth(alleUser.getCountryOfBirth());
        AllUserDto.setBankCode(alleUser.getBankCode());
        AllUserDto.setIban(alleUser.getIban());
        AllUserDto.setDescription(alleUser.getDescription());
        AllUserDto.setPicture(alleUser.getPicture());
        AllUserDto.setUserRoleIds(alleUser.getUserRoleIds());
        AllUserDto.setTutorsIds(alleUser.getTutorsIds());
        AllUserDto.setAppointmentIds(alleUser.getAppointmentIds());
        AllUserDto.setFachIds(alleUser.getFachIds());
        AllUserDto.setContractIds(alleUser.getContractIds());
        AllUserDto.setSchoolTutorsIds(alleUser.getSchoolTutorsIds());
        AllUserDto.setAvailabilityTime(alleUser.getAvailabilityTime());

        AllUserDto.setAccountActive(alleUser.isAccountActive());
        AllUserDto.setTaxId(alleUser.getTaxId());
        AllUserDto.setHobbies(alleUser.getHobbies());
        AllUserDto.setZoomLink(alleUser.getZoomLink());

        AllUserDto.setParentFirstName(alleUser.getParentFirstName());
        AllUserDto.setParentLastName(alleUser.getParentLastName());
        AllUserDto.setSchoolLevel(alleUser.getSchoolLevel());


        return AllUserDto;
    }

    public AllUser login(String emailOrPhoneNumber, String password) {
        Optional<AllUser> AllUserOptional;

        if (emailOrPhoneNumber.contains("@")) {
            AllUserOptional = AllUserDao.findByEmailAddress(emailOrPhoneNumber);
        } else {
            AllUserOptional = AllUserDao.findByPhoneNumber(emailOrPhoneNumber);
        }

        if (AllUserOptional.isEmpty()) {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }

        AllUser AllUser = AllUserOptional.get();

        if (password.equals(passwordEncryptionUtil.decrypt(AllUser.getPassword()))) {
            return AllUser;
        } else {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }
    }

    public List<AllUserDto> createUsersIgnoreErrors(List<AllUserDto> AllUserDtoList) {
        return AllUserDtoList.stream()
                .map(AllUserDto -> {
                    try {
                        return createUsers(AllUserDto);
                    } catch (UserAlreadyExistsException e) {
                        // Optional: Logging
                        System.out.println("Fehler beim Erstellen von Tutor: " + e.getMessage());
                        return null;
                    }
                })
                .filter(AllUserDto -> AllUserDto != null)
                .collect(Collectors.toList());
    }


}
