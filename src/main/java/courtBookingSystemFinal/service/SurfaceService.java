package courtBookingSystemFinal.service;
import courtBookingSystemFinal.model.Surface;
import java.util.List;


public interface SurfaceService {

    Surface newSurface(Surface surface);
    List<Surface> getSurfaces();
    void deleteSurfaceById(Long id);

}
