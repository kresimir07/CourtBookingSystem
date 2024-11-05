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
    public List<Court>getAllCourts() {
        return courtService.getAllCourts();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void newCourt(@RequestBody Court court) {
        courtService.newCourt(court);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Court updateCourt(@PathVariable Long id, @RequestBody Court updatedCourt) {
        return courtService.updateCourt(id, updatedCourt);
    }

    @PutMapping("/surface-to-court")
    @ResponseStatus(HttpStatus.OK)
    public void addOrModifySurfaceToCourt(@RequestBody Court court) {
        courtService.addOrModifySurfaceToCourt(court.getId(), court.getSurface().getId());
    }


}
