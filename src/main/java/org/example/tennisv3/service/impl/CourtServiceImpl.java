package org.example.tennisv3.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tennisv3.dto.CourtResponseDTO;
import org.example.tennisv3.model.Court;
import org.example.tennisv3.repository.CourtRepository;
import org.example.tennisv3.service.CourtService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourtServiceImpl implements CourtService {


    private final CourtRepository courtRepository;


    @Override
    public void newCourt(Court court) {
        log.info("Saving court name {} to the database", court.getName());
        courtRepository.save(court);
    }

    @Override
    public List<Court> getAllCourts() {
        log.info("Fetching all court names");
        return courtRepository.findAll();
    }

    @Override
    public CourtResponseDTO updateCourt(Long id, Court updatedCourt) {
        log.info("Updating court with id {}", id);
        return courtRepository.findById(id).map(court -> {
            court.setName(updatedCourt.getName());
            court.setSurface(updatedCourt.getSurface());
            court.setIndoor(updatedCourt.isIndoor());
            Court savedCourt = courtRepository.save(court);
            return new CourtResponseDTO("Court updated successfully", savedCourt);
        }).orElseThrow(() -> {
            String errorMsg = "Court with id " + id +" not found";
            log.error(errorMsg);
            return new RuntimeException(errorMsg);
        });
    }

}



