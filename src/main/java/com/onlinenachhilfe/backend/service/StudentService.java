package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.StudentDao;
import com.onlinenachhilfe.backend.dto.StudentDto;
import com.onlinenachhilfe.backend.dto.TutorDto;
import com.onlinenachhilfe.backend.exception.InvalidCredentialsException;
import com.onlinenachhilfe.backend.exception.UserAlreadyExistsException;
import com.onlinenachhilfe.backend.model.Student;
import com.onlinenachhilfe.backend.model.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

   private StudentDao studentDao;
    private PasswordEncryptionUtil passwordEncryptionUtil;
  @Autowired
    public StudentService(StudentDao studentDao, PasswordEncryptionUtil passwordEncryptionUtil) {
        this.studentDao = studentDao;
        this.passwordEncryptionUtil = passwordEncryptionUtil;
    }

    public StudentDto createStudent(StudentDto studentDto) {
        // Überprüfe, ob die E-Mail oder Telefonnummer bereits existiert
        if (studentDao.findByEmailAddress(studentDto.getEmailAddress()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Student mit dieser E-Mail-Adresse existiert bereits.");
        }

        if (studentDao.findByPhoneNumber(studentDto.getPhoneNumber()).isPresent()) {
            throw new UserAlreadyExistsException("Ein Student mit dieser Telefonnummer existiert bereits.");
        }
        Student student = convertToEntity(studentDto);
        Student savedStudent = studentDao.save(student);
        return convertToDto(savedStudent);
    }

    public List<StudentDto> getAllStudents() {
        return studentDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<StudentDto> getStudentById(Long id) {
        return studentDao.findById(id).map(this::convertToDto);
    }

    public Optional<StudentDto> getStudentByEmail(String email) {
        return studentDao.findByEmailAddress(email).map(this::convertToDto);
    }

    public void deleteStudent(Long id) {
        studentDao.deleteById(id);
    }

    public List<StudentDto> getStudentsByLastName(String lastName) {
        return studentDao.findByLastName(lastName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByFirstName(String firstName) {
        return studentDao.findByFirstName(firstName).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByAddress(String street, String houseNumber, String postalCode) {
        return studentDao.findByStreetAndHouseNumberAndPostalCode(street, houseNumber, postalCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<StudentDto> getStudentByPhoneNumber(String phoneNumber) {
        return studentDao.findByPhoneNumber(phoneNumber).map(this::convertToDto);
    }

    public List<StudentDto> getStudentsByDateOfBirth(LocalDate dateOfBirth) {
        return studentDao.findByDateOfBirth(dateOfBirth).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<StudentDto> getStudentByIban(String iban) {
        return studentDao.findByIban(iban).map(this::convertToDto);
    }

    public List<StudentDto> getStudentsByParentFirstName(String parentFirstName) {
        return studentDao.findByParentFirstName(parentFirstName).stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public void deleteStudentByEmail(String email) {
        Student student = studentDao.findByEmailAddress(email).orElseThrow(() ->
                new IllegalArgumentException("Student with Email " + email + " not found."));
        studentDao.delete(student);
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {

        Student existingStudent = studentDao.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Student with ID " + id + " not found."));

        existingStudent =convertToEntity(studentDto);

        Student updatedStudent = studentDao.save(existingStudent);

        return convertToDto(updatedStudent);
    }
    public void deleteStudentByPhoneNumber(String phoneNumber) {
        Student student = studentDao.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new IllegalArgumentException("Student with Phone Number " + phoneNumber + " not found."));
        studentDao.delete(student);
    }

    private Student convertToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setSalutation(studentDto.getSalutation());
        student.setTitle(studentDto.getTitle());
        student.setLastName(studentDto.getLastName());
        student.setFirstName(studentDto.getFirstName());
        student.setEmailAddress(studentDto.getEmailAddress());
        student.setPassword(passwordEncryptionUtil.encrypt(studentDto.getPassword()));
        student.setStreet(studentDto.getStreet());
        student.setHouseNumber(studentDto.getHouseNumber());
        student.setPostalCode(studentDto.getPostalCode());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setCity(studentDto.getCity());
        student.setCountry(studentDto.getCountry());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPlaceOfBirth(studentDto.getPlaceOfBirth());
        student.setCountryOfBirth(studentDto.getCountryOfBirth());
        student.setBankCode(studentDto.getBankCode());
        student.setIban(studentDto.getIban());
        student.setDescription(studentDto.getDescription());
        student.setPicture(studentDto.getPicture());

        student.setUserRoleIds(studentDto.getUserRoleIds());
        student.setTutorsIds(studentDto.getTutorsIds());
        student.setAppointmentIds(studentDto.getAppointmentIds());
        student.setFachIds(studentDto.getFachIds());
        student.setContractIds(studentDto.getContractIds());
        student.setSchoolTutorsIds(studentDto.getSchoolTutorsIds());

        student.setAccountActive(studentDto.isAccountActive());
        student.setParentFirstName(studentDto.getParentFirstName());
        student.setParentLastName(studentDto.getParentLastName());
        student.setSchoolLevel(studentDto.getSchoolLevel());
        return student;
    }

    private StudentDto convertToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setSalutation(student.getSalutation());
        studentDto.setTitle(student.getTitle());
        studentDto.setLastName(student.getLastName());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setEmailAddress(student.getEmailAddress());
        studentDto.setPassword(passwordEncryptionUtil.decrypt(student.getPassword()));
        studentDto.setStreet(student.getStreet());
        studentDto.setHouseNumber(student.getHouseNumber());
        studentDto.setPostalCode(student.getPostalCode());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        studentDto.setCity(student.getCity());
        studentDto.setCountry(student.getCountry());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setPlaceOfBirth(student.getPlaceOfBirth());
        studentDto.setCountryOfBirth(student.getCountryOfBirth());
        studentDto.setBankCode(student.getBankCode());
        studentDto.setIban(student.getIban());
        studentDto.setDescription(student.getDescription());
        studentDto.setPicture(student.getPicture());
        studentDto.setAccountActive(student.isAccountActive());
        studentDto.setParentFirstName(student.getParentFirstName());
        studentDto.setParentLastName(student.getParentLastName());
        studentDto.setSchoolLevel(student.getSchoolLevel());
        studentDto.setUserRoleIds(student.getUserRoleIds());
        studentDto.setTutorsIds(student.getTutorsIds());
        studentDto.setAppointmentIds(student.getAppointmentIds());
        studentDto.setFachIds(student.getFachIds());
        studentDto.setContractIds(student.getContractIds());
        studentDto.setSchoolTutorsIds(student.getSchoolTutorsIds());
        return studentDto;
    }
    public String login(String emailOrPhoneNumber, String password) {

        Optional<Student> studentOptional;

        if (emailOrPhoneNumber.contains("@")) {
            studentOptional = studentDao.findByEmailAddress(emailOrPhoneNumber);
        } else {
            studentOptional = studentDao.findByPhoneNumber(emailOrPhoneNumber);
        }

        if (studentOptional.isEmpty()) {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }

       Student student = studentOptional.get();

        if (password.equals(passwordEncryptionUtil.decrypt(student.getPassword()))) {
            return "Login erfolgreich!";
        } else {
            throw new InvalidCredentialsException("E-Mail/Telfonnummer oder Passwort sind ungültig.");
        }
    }
}
