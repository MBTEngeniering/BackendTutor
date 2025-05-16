package de.fairstudy.backend.service;

import de.fairstudy.backend.Utils.UUIDGenerator;
import de.fairstudy.backend.repository.AllUserDao;
import de.fairstudy.backend.repository.ContractDao;
import de.fairstudy.backend.dto.ContractDto;
import de.fairstudy.backend.entity.AllUser;
import de.fairstudy.backend.entity.Contract;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContractService {

    private final ContractDao contractDao;

    private final AllUserDao allUserDao;

    public ContractService(ContractDao contractDao, AllUserDao allUserDao) {
        this.contractDao = contractDao;
        this.allUserDao = allUserDao;
    }

    public ContractDto saveContract(ContractDto contractDto) {
        Optional<AllUser> optionalUser = allUserDao.findById(contractDto.getOwnerId());

        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User with ID " + contractDto.getOwnerId() + " not found");
        }

        AllUser user = optionalUser.get();
        UUID uid= new UUIDGenerator().generateUUID();
        contractDto.setOwnerId(uid);
        Contract contract = convertToEntity(contractDto);
        Contract savedContract = contractDao.save(contract);

        user.getContractIds().add(savedContract.getId());
        allUserDao.save(user);

        return convertToDto(savedContract);
    }

    public Optional<ContractDto> getContractById(UUID id) {
        return contractDao.findById(id).map(this::convertToDto);
    }

    public List<ContractDto> getContractsByOwnerId(UUID ownerId) {
        return contractDao.findByOwnerId(ownerId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<ContractDto> getContractsByCreationDate(Date creationDate) {
        return contractDao.findByCreationDate(creationDate)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<ContractDto> getAllContracts() {
        return contractDao.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public void deleteContract(UUID id) {
        Optional<Contract> optionalContract = contractDao.findById(id);

        if (optionalContract.isPresent()) {
            Contract contract = optionalContract.get();
            Optional<AllUser> optionalUser = allUserDao.findById(contract.getOwnerId());

            optionalUser.ifPresent(user -> {
                user.getContractIds().remove(id);
                allUserDao.save(user);
            });

            contractDao.deleteById(id);
        }
    }

    public ContractDto updateContract(ContractDto contractDto) {
        Optional<Contract> optionalContract = contractDao.findById(contractDto.getId());

        if (optionalContract.isEmpty()) {
            throw new NoSuchElementException("Contract with ID " + contractDto.getId() + " not found");
        }

        Contract contract = convertToEntity(contractDto);
        Contract updated = contractDao.save(contract);
        return convertToDto(updated);
    }

    public Contract convertToEntity(ContractDto dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setOwnerId(dto.getOwnerId());
        contract.setContent(dto.getContent());
        contract.setCreationDate(dto.getCreationDate());
        contract.setContractDuration(dto.getContractDuration());
        contract.setContractPrice(dto.getContractPrice());
        return contract;
    }

    public ContractDto convertToDto(Contract contract) {
        ContractDto dto = new ContractDto();
        dto.setId(contract.getId());
        dto.setOwnerId(contract.getOwnerId());
        dto.setContent(contract.getContent());
        dto.setCreationDate(contract.getCreationDate());
        dto.setContractDuration(contract.getContractDuration());
        dto.setContractPrice(contract.getContractPrice());
        return dto;
    }
}
