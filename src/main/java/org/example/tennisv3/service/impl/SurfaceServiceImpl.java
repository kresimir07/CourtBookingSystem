package org.example.tennisv3.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.tennisv3.model.Surface;
import org.example.tennisv3.repository.SurfaceRepository;
import org.example.tennisv3.service.SurfaceService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurfaceServiceImpl implements SurfaceService {

    private final SurfaceRepository surfaceRepository;



    @Override
    public Surface saveSurfaceType(Surface surface) {
        log.info("Saving new surface {} to the database", surface.getName());
        return surfaceRepository.save(surface);
    }
    @Override
    public List<Surface> getSurfaceTypes() {
        log.info("Fetching all surface types");
        return surfaceRepository.findAll();
    }

}
