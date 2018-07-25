package edu.khusaenov.example.helmestesttask.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Khusaenov on 20.07.2018
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean agreement;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;

    public User copy() {
        User copy = new User();
        copy.setSessionId(getSessionId());
        copy.setName(getName());
        copy.setAgreement(getAgreement());
        if (getSector() != null) {
            copy.setSector(getSector().copy());
        }
        return copy;
    }

    public boolean containsSectorId() {
        return sector != null && sector.getSectorId() != null;
    }


}
