package edu.khusaenov.example.helmestesttask.utils;

import edu.khusaenov.example.helmestesttask.model.Sector;
import edu.khusaenov.example.helmestesttask.model.User;
import java.util.Collections;
import java.util.UUID;


/**
 * @author Khusaenov on 25.07.2018
 */
public class DataCreator {

    public Sector createSector(String sectorId) {
        Sector sector = new Sector();
        sector.setSectorId(Long.parseLong(sectorId));
        return sector;
    }

    public User createUser(String sectorId) {
        User user = new User();
        user.setAgreement(true);
        user.setName(UUID.randomUUID().toString());
        user.setSectors(Collections.singletonList(createSector(sectorId)));
        return user;
    }

}
