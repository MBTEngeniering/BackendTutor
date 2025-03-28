package com.onlinenachhilfe.backend.service;

import com.onlinenachhilfe.backend.dao.ContractDao;
import com.onlinenachhilfe.backend.dao.StudentDao;
import com.onlinenachhilfe.backend.dao.TutorDao;
import com.onlinenachhilfe.backend.dto.ContractDto;
import com.onlinenachhilfe.backend.model.Contract;
import com.onlinenachhilfe.backend.model.Tutor;
import com.onlinenachhilfe.backend.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private TutorDao tutorDao;

    @Autowired
    private StudentDao studentDao;

    public ContractDto convertToDto(Contract contract) {
        return new ContractDto(
                contract.getId(),
                contract.getContractorId(),
                contract.getContracteId(),
                contract.getContractContent(),
                contract.getHourlyRate(),
                contract.getHoursPerMonth(),
                contract.getContracteeStatus()
        );
    }

    public Contract convertToEntity(ContractDto contractDto) {
        return new Contract(
                contractDto.getId(),
                contractDto.getContractorId(),
                contractDto.getContracteId(),
                contractDto.getContractContent(),
                contractDto.getHourlyRate(),
                contractDto.getHoursPerMonth(),
                contractDto.getContracteeStatus()
        );
    }

    public ContractDto findById(Long id) {
        return contractDao.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
    }

    public ContractDto create(ContractDto contractDto) {
        Long contracteId = contractDto.getContracteId();
        String contracteeStatus = contractDto.getContracteeStatus();

        Optional<Student> student = Optional.empty();
        Optional<Tutor> tutor = Optional.empty();

        if ("STUDENT".equalsIgnoreCase(contracteeStatus)) {
            student = studentDao.findById(contracteId);
        } else {
            tutor = tutorDao.findById(contracteId);
        }

        if (student.isPresent() || tutor.isPresent()) {
            Contract contract = convertToEntity(contractDto);
            Contract savedContract = contractDao.save(contract);

            if (student.isPresent()) {
                Student foundStudent = student.get();
                foundStudent.getContractIds().add(savedContract.getId());
                studentDao.save(foundStudent);
            } else {
                Tutor foundTutor = tutor.get();
                foundTutor.getContractIds().add(savedContract.getId());
                tutorDao.save(foundTutor);
            }

            return convertToDto(savedContract);
        } else {
            throw new RuntimeException("Neither Student nor Tutor found with ID: " + contracteId);
        }
    }

    public ContractDto update(Long id, ContractDto contractDto) {
        Contract existingContract = contractDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

        existingContract.setContractorId(contractDto.getContractorId());
        existingContract.setContracteId(contractDto.getContracteId());
        existingContract.setContractContent(contractDto.getContractContent());
        existingContract.setHourlyRate(contractDto.getHourlyRate());
        existingContract.setHoursPerMonth(contractDto.getHoursPerMonth());
        existingContract.setContracteeStatus(contractDto.getContracteeStatus());

        Contract updatedContract = contractDao.save(existingContract);

        return convertToDto(updatedContract);
    }

    public void delete(Long id) {
        Contract contract = contractDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));
        contractDao.delete(contract);
    }
}
