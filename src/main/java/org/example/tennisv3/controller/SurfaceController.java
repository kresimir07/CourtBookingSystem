package org.example.tennisv3.controller;
import lombok.RequiredArgsConstructor;
import org.example.tennisv3.model.Surface;
import org.example.tennisv3.service.SurfaceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SurfaceController {


    private final SurfaceService surfaceService;

    @GetMapping("/surface")
    @ResponseStatus(HttpStatus.OK)
    public List<Surface> getSurfaceTypes() {
        return surfaceService.getSurfaceTypes();
    }

    @PostMapping("/surface/surface-to-save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSurfaceType(@RequestBody Surface surface) {
        surfaceService.saveSurfaceType(surface);

    }





}
