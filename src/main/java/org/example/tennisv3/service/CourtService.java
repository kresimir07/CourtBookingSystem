package org.example.tennisv3.service;
import org.example.tennisv3.model.Court;

import java.util.List;

public interface CourtService {


    void newCourt(Court court);
    List<Court> getAllCourts();
    Court updateCourt(Long id, Court updatedCourt);
    void addOrModifySurfaceToCourt(Long id, Long surfaceId);
//    void removeSurfaceFromCourt(Long id, Long surfaceId);



}
