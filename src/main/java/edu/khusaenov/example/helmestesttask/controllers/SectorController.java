package edu.khusaenov.example.helmestesttask.controllers;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.service.SectorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Khusaenov on 22.07.2018
 */
@RestController
@RequestMapping("/sector")
public class SectorController {

    private final SectorService sectorService;

    @Autowired
    public SectorController(
            SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sector> getSectors() {
        return sectorService.getRecursiveSector();
    }
}
