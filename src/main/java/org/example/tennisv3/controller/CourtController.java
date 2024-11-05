package org.example.tennisv3.controller;

import lombok.RequiredArgsConstructor;
import org.example.tennisv3.model.Court;
import org.example.tennisv3.service.CourtService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courts")
@RequiredArgsConstructor
public class CourtController {


    private final CourtService courtService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Court>getCourtNames() {
        return courtService.getCourtNames();
    }

    @PostMapping("/courts-to-save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourtName(@RequestBody Court court) {
        courtService.saveCourtName(court);
    }



}
