package edu.khusaenov.example.helmestesttask.service;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
        List<Sector> rootSectorList = sectorRepository.findByParentIsNull();
        rootSectorList.forEach(this::recursiveTree);
        return rootSectorList;
    }

    private void recursiveTree(Sector sector) {
        log.debug("Sector name is {}", sector.getLabel());
        if (!CollectionUtils.isEmpty(sector.getChildren())) {
            sector.getChildren().forEach(this::recursiveTree);

        }
    }
}

