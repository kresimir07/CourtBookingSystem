package org.example.tennisv3.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public Court saveCourtName(Court court) {
        log.info("Saving court name {} to the database", court.getName());
        return courtRepository.save(court);
    }

    @Override
    public List<Court>getCourtNames() {
        log.info("Fetching all court names");
        return courtRepository.findAll();
    }



}
