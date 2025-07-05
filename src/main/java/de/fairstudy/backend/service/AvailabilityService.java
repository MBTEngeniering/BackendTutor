package de.fairstudy.backend.service;


import de.fairstudy.backend.dto.AvailabilityDto;
import de.fairstudy.backend.entity.Availability;
import de.fairstudy.backend.repository.AvailabilityDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityDao availabilityDao;

    public boolean existsByAllFields(AvailabilityDto dto) {
        return availabilityDao.existsByUserIdAndTagAndMonatAndJahrAndUhrzeit(
                dto.getUserId(),
                dto.getTag(),
                dto.getMonat(),
                dto.getJahr(),
                dto.getUhrzeit()
        );
    }

    public Availability createAvailability(AvailabilityDto dto) {
        Availability availability = Availability.builder()
                .userId(dto.getUserId())
                .tag(dto.getTag())
                .monat(dto.getMonat())
                .jahr(dto.getJahr())
                .uhrzeit(dto.getUhrzeit())
                .build();
        return availabilityDao.save(availability);
    }

    public List<AvailabilityDto> getAllByUserId(UUID userId) {
        return availabilityDao.findByUserId(userId).stream()
                .map(a -> AvailabilityDto.builder()
                        .id(a.getId())
                        .userId(a.getUserId())
                        .tag(a.getTag())
                        .monat(a.getMonat())
                        .jahr(a.getJahr())
                        .uhrzeit(a.getUhrzeit())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Availability> getAll() {
        return availabilityDao.findAll();
    }

    public void deleteAvailability(Long id) {
        availabilityDao.deleteById(id);
    }
    @Transactional
    public void deleteByUhrzeitAndUserId(String uhrzeit, UUID userId) {
        availabilityDao.deleteByUhrzeitAndUserId(uhrzeit,userId);
    }


}
