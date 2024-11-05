package org.example.tennisv3.controller;
import lombok.RequiredArgsConstructor;
import org.example.tennisv3.model.Surface;
import org.example.tennisv3.service.SurfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/surface")
@RequiredArgsConstructor
public class SurfaceController {


    private final SurfaceService surfaceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Surface> getSurfaces() {
        return surfaceService.getSurfaces();
    }

    @PostMapping("/new-surface")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSurfaceType(@RequestBody Surface surface) {
        surfaceService.newSurface(surface);
    }
    
    
//    This works only if surface is not assigned to any of the courts. If assigned surface needs to be deleted,
//    first it needs to be unassigned from court, which can be done through Court edit
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        surfaceService.deleteSurfaceById(id);
    }





}
