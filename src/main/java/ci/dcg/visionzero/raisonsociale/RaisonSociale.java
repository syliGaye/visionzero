package ci.dcg.visionzero.raisonsociale;

import ci.dcg.visionzero.entreprise.Entreprise;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
public class RaisonSociale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_RAISON_SOCIALE", nullable = false, updatable = false)
    private String idRaisonSociale;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_RAISON_SOCIALE", unique = true, nullable = false)
    private String libelleRaisonSociale;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "raisonSociale")
    private List<Entreprise> entrepriseList;

    public RaisonSociale() {
        super();
    }

    public RaisonSociale(String libelleRaisonSociale) {
        this.libelleRaisonSociale = libelleRaisonSociale;
    }

    public String getIdRaisonSociale() {
        return idRaisonSociale;
    }

    public void setIdRaisonSociale(String idRaisonSociale) {
        this.idRaisonSociale = idRaisonSociale;
    }

    public String getLibelleRaisonSociale() {
        return libelleRaisonSociale;
    }

    public void setLibelleRaisonSociale(String libelleRaisonSociale) {
        this.libelleRaisonSociale = libelleRaisonSociale;
    }

    @XmlTransient
    public List<Entreprise> getEntrepriseList() {
        return entrepriseList;
    }

    public void setEntrepriseList(List<Entreprise> entrepriseList) {
        this.entrepriseList = entrepriseList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RaisonSociale that = (RaisonSociale) o;

        return idRaisonSociale != null ? idRaisonSociale.equals(that.idRaisonSociale) : that.idRaisonSociale == null;
    }

    @Override
    public int hashCode() {
        return idRaisonSociale != null ? idRaisonSociale.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RaisonSociale{" +
                "idRaisonSociale='" + idRaisonSociale + '\'' +
                ", libelleRaisonSociale='" + libelleRaisonSociale + '\'' +
                ", entrepriseList=" + entrepriseList +
                '}';
    }
}
