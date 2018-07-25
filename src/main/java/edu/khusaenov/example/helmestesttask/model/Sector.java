package edu.khusaenov.example.helmestesttask.model;

import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

/**
 * @author Khusaenov on 20.07.2018
 */
@Entity
@Table(name = "sectors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Sector implements Serializable {

    @Id
    @Column(name = "sector_id")
    private Long sectorId;

    private String label;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_id")
    private Sector parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Sector> children = new ArrayList<>();


    Sector copy() {
        Sector copy = new Sector();
        copy.setSectorId(getSectorId());
        copy.setLabel(getLabel());
        if (!CollectionUtils.isEmpty(getChildren())) {
            copy.setChildren(new ArrayList<>(getChildren()));
        }
        if (getParent() != null) {
            copy.setParent(getParent().copy());
        }
        return copy;
    }
}
