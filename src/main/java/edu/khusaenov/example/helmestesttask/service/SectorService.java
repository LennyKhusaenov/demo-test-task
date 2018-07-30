package edu.khusaenov.example.helmestesttask.service;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Khusaenov on 22.07.2018
 */
@Slf4j
@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorService(
            SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public List<Sector> getRecursiveSector() {
        List<Sector> rootSectorList = sectorRepository.findByParentIsNull()
                .orElse(Collections.emptyList());
        log.debug("Sectors data from database = {}", rootSectorList);
        return rootSectorList;
    }

}

