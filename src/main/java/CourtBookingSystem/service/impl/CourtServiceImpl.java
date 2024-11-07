package CourtBookingSystem.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import CourtBookingSystem.dto.CourtResponseDTO;
import CourtBookingSystem.model.Court;
import CourtBookingSystem.model.Surface;
import CourtBookingSystem.repository.CourtRepository;
import CourtBookingSystem.repository.SurfaceRepository;
import CourtBookingSystem.service.CourtService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourtServiceImpl implements CourtService {


    private final CourtRepository courtRepository;
    private final SurfaceRepository surfaceRepository;


    @Override
    public Court newCourt(Court court) {
        log.info("Saving court name {} to the database", court.getName());
        courtRepository.save(court);
        return court;
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

    @Override
    public void addOrModifySurfaceToCourt(Long id, Long surfaceId) {
        log.info("Adding surface with id {} to court with id {}", surfaceId, id);
        Court court = courtRepository.findById(id).orElse(null);
        Surface surface = surfaceRepository.findById(surfaceId).orElse(null);
        if (court != null && surface != null) {
            court.setSurface(surface);
            courtRepository.save(court);
        } else {
            throw new RuntimeException("Court or surface not found");
        }
    }

}



