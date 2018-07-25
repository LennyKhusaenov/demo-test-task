package edu.khusaenov.example.helmestesttask.converter;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.repository.SectorRepository;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author Khusaenov on 24.07.2018
 */
@Slf4j
@Component
public class SectorConverter implements Converter<String, Sector> {

    private final SectorRepository sectorRepository;

    @Autowired
    public SectorConverter(
            SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Override
    public Sector convert(String sectorId) {
        if (!StringUtils.isEmpty(sectorId)) {
            log.debug("Try to get data by sectorId = {}", sectorId);
            long intSectorId = Long.parseLong(sectorId);
            return sectorRepository.findBySectorId(intSectorId)
                    .orElseThrow(NoSuchElementException::new);
        }
        throw new NoSuchElementException();
    }
}
