package courtBookingSystem.service;
import courtBookingSystem.model.Court;
import java.util.List;

public interface CourtService {


    Court newCourt(Court court);
    List<Court> getAllCourts();
    Court updateCourt(Long id, Court updatedCourt);
    void addOrModifySurfaceToCourt(Long id, Long surfaceId);

}
