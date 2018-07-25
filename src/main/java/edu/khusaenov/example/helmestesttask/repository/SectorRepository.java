package edu.khusaenov.example.helmestesttask.repository;

import edu.khusaenov.example.helmestesttask.model.Sector;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Khusaenov on 22.07.2018
 */
@Repository
public interface SectorRepository extends CrudRepository<Sector, String> {

    Optional<List<Sector>> findByParentIsNull();

    Optional<Sector> findBySectorId(long sectorId);

}
