package edu.khusaenov.example.helmestesttask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


/**
 * @author Khusaenov on 20.07.2018
 */
@Data
@Entity
@Table(name = "users")
@Slf4j
public class User {

    @Id
    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean agreement;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_sector", joinColumns = {
            @JoinColumn(name = "session_id")}, inverseJoinColumns = {
            @JoinColumn(name = "sector_id")})
    private List<Sector> sectors = new ArrayList<>();

    public User copy() {
        User copy = new User();
        copy.setSessionId(getSessionId());
        copy.setName(getName());
        copy.setAgreement(getAgreement());
        if (getSectors() != null) {
            copy.setSectors(
                    getSectors().stream().map(Sector::copy).collect(Collectors.toList()));
        }
        return copy;
    }
    public boolean isUsersSector(Long sectorId) {
        return !CollectionUtils.isEmpty(sectors) && !CollectionUtils.isEmpty(sectors.stream()
                .filter(sector -> sectorId.equals(sector.getSectorId()))
                .collect(Collectors.toList()));

    }


}
