package ci.dcg.visionzero.continent;

import ci.dcg.visionzero.pays.Pays;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlRootElement
public class Continent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_CONTINENT", updatable = false, nullable = false)
    private String idContinent;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_CONTINENT", unique = true, nullable = false)
    private String libelleContinent;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "continent")
    private List<Pays> paysList;

    public Continent() {
        super();
    }

    public Continent(String libelleContinent) {
        this.libelleContinent = libelleContinent;
    }

    public Continent(String idContinent, String libelleContinent) {
        this.idContinent = idContinent;
        this.libelleContinent = libelleContinent;
    }

    public String getIdContinent() {
        return idContinent;
    }

    public void setIdContinent(String idContinent) {
        this.idContinent = idContinent;
    }

    public String getLibelleContinent() {
        return libelleContinent;
    }

    public void setLibelleContinent(String libelleContinent) {
        this.libelleContinent = libelleContinent;
    }

    @XmlTransient
    public List<Pays> getPaysList() {
        return paysList;
    }

    public void setPaysList(List<Pays> paysList) {
        this.paysList = paysList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Continent continent = (Continent) o;

        return idContinent != null ? idContinent.equals(continent.idContinent) : continent.idContinent == null;
    }

    @Override
    public int hashCode() {
        return idContinent != null ? idContinent.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "idContinent='" + idContinent + '\'' +
                ", libelleContinent='" + libelleContinent + '\'' +
                ", paysList=" + paysList +
                '}';
    }
}
