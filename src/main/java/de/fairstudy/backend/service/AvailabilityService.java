package de.fairstudy.backend.service;


import de.fairstudy.backend.dto.AvailabilityDto;
import de.fairstudy.backend.entity.Availability;
import de.fairstudy.backend.repository.AvailabilityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void deleteByAllFields(AvailabilityDto dto) {
         List<Availability> todelete= getAll();
         System.out.println(todelete.size());
          for (Availability availability : todelete) {
              if(availability.getUserId().equals(dto.getUserId())&&availability.getTag().equals(dto.getTag())&&availability.getMonat().equals(dto.getMonat())
              &&availability.getJahr().equals(dto.getJahr())&&availability.getUhrzeit().equals(dto.getUhrzeit())) {
                                  availabilityDao.deleteById(availability.getId());
              }


          }

    }

}
