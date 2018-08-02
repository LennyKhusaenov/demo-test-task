package edu.khusaenov.example.helmestesttask.service.impl;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import edu.khusaenov.example.helmestesttask.service.SectorService;
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
public class SectorServiceImpl implements SectorService {

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorServiceImpl(
            SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public List<Sector> getRecursiveSector() {
        List<Sector> rootSectorList = sectorRepository.findByParentIsNull()
                .orElse(Collections.emptyList());
        log.debug("Sectors data from database = {}", rootSectorList);
        return rootSectorList;
    }

}

