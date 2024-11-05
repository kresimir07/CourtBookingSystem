package org.example.tennisv3.service.impl;
import lombok.extern.slf4j.Slf4j;
import org.example.tennisv3.model.Court;
import org.example.tennisv3.model.Surface;
import org.example.tennisv3.repository.CourtRepository;
import org.example.tennisv3.repository.SurfaceRepository;
import org.example.tennisv3.service.SurfaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class SurfaceServiceImpl implements SurfaceService {

    private final SurfaceRepository surfaceRepository;
    private CourtRepository courtRepository;

    public SurfaceServiceImpl(SurfaceRepository surfaceRepository) {
        this.surfaceRepository = surfaceRepository;
    }

    @Override
    public List<Surface> getSurfaces() {
        log.info("Fetching all surface types");
        return surfaceRepository.findAll();
    }

    @Transactional
    public Surface newSurface(Surface surface) {
        log.info("Saving new surface {} to the database", surface.getName());
        return surfaceRepository.save(surface);
    }

    @Transactional
    public void deleteSurfaceById(Long id) {
        log.info("Deleting surface with id {}", id);
        surfaceRepository.findById(id).map(surface -> {
            surfaceRepository.delete(surface);
            return surface;
        }).orElseThrow(() -> {
            String errorMsg2 = "Surface with id " + id + " not found";
            log.error(errorMsg2);
            return new RuntimeException(errorMsg2);
        });
    }


}
