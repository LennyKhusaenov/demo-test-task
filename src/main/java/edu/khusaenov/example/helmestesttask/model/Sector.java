package edu.khusaenov.example.helmestesttask.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Khusaenov on 20.07.2018
 */
@Entity
@Table(name = "sectors")
@Getter
@Setter
@EqualsAndHashCode
public class Sector {

    @Id
    @Column(name = "sector_id")
    private Long sectorId;

    private String label;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    private Sector parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sector> children = new ArrayList<>();

    @Override
    public String toString() {
        return "Sector{" +
                "id=" + sectorId +
                ", label='" + label + '\'' +
                '}';
    }

}
