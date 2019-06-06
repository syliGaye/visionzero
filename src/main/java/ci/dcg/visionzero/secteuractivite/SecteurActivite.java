package ci.dcg.visionzero.secteuractivite;

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
public class SecteurActivite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_SECTEUR_ACTIVITE", nullable = false, updatable = false)
    private String idSecteurActivite;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_SECTEUR_ACTIVITE", unique = true, nullable = false)
    private String libelleSecteurActivite;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secteurActivite")
    private List<Entreprise> entrepriseList;

    public SecteurActivite() {
        super();
    }

    public SecteurActivite(String idSecteurActivite) {
        this.idSecteurActivite = idSecteurActivite;
    }

    public String getIdSecteurActivite() {
        return idSecteurActivite;
    }

    public void setIdSecteurActivite(String idSecteurActivite) {
        this.idSecteurActivite = idSecteurActivite;
    }

    public String getLibelleSecteurActivite() {
        return libelleSecteurActivite;
    }

    public void setLibelleSecteurActivite(String libelleSecteurActivite) {
        this.libelleSecteurActivite = libelleSecteurActivite;
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

        SecteurActivite that = (SecteurActivite) o;

        return idSecteurActivite != null ? idSecteurActivite.equals(that.idSecteurActivite) : that.idSecteurActivite == null;
    }

    @Override
    public int hashCode() {
        return idSecteurActivite != null ? idSecteurActivite.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SecteurActivite{" +
                "idSecteurActivite='" + idSecteurActivite + '\'' +
                ", libelleSecteurActivite='" + libelleSecteurActivite + '\'' +
                ", entrepriseList=" + entrepriseList +
                '}';
    }
}
