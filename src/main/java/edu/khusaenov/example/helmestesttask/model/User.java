package edu.khusaenov.example.helmestesttask.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@Slf4j
@EqualsAndHashCode
@ToString
public class User {

    @Id
    @Column(name = "session_id")
    private String sessionId;

    private String name;

    private Boolean agreement;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sector_id")
    private Sector sectorId;


}
