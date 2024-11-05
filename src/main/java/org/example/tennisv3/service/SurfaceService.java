package org.example.tennisv3.service;
import org.example.tennisv3.model.Surface;
import java.util.List;


public interface SurfaceService {

    Surface newSurface(Surface surface);
    List<Surface> getSurfaces();
    void deleteSurfaceById(Long id);




}
