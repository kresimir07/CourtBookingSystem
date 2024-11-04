package org.example.tennisv3.service;
import org.example.tennisv3.model.Court;
import java.util.List;

public interface CourtService {

    void saveCourtName(Court court);
    List<Court> getCourtNames();


}
