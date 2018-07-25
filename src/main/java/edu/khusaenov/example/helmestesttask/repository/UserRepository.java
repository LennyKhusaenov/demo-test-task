package edu.khusaenov.example.helmestesttask.repository;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.model.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Khusaenov on 20.07.2018
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findUserBySessionId(String sessionId);

    Optional<User> findUserBySectorLike(Sector sector);

}
