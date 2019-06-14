package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.continent.Continent;
import ci.dcg.visionzero.entreprise.Entreprise;
import ci.dcg.visionzero.isoalpha.IsoAlphaDeux;
import ci.dcg.visionzero.isoalpha.IsoAlphaTrois;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_PAYS", nullable = false, updatable = false)
    private String idPays;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_PAYS", unique = true, nullable = false)
    private String libellePays;

    @JsonManagedReference
    @JoinColumn(name = "CODE_CONTINENT", referencedColumnName = "CODE_CONTINENT")
    @ManyToOne(optional = false)
    private Continent continent;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pays")
    private IsoAlphaDeux isoAlphaDeux;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pays")
    private IsoAlphaTrois isoAlphaTrois;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pays")
    private List<Entreprise> entrepriseList;

    public Pays() {
        super();
    }

    public Pays(String idPays) {
        this.idPays = idPays;
    }

    public Pays(String idPays, String libellePays) {
        this.idPays = idPays;
        this.libellePays = libellePays;
    }

    public Pays(String libellePays, Continent continent) {
        this.continent = continent;
        this.libellePays = libellePays;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    public String getLibellePays() {
        return libellePays;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public IsoAlphaDeux getIsoAlphaDeux() {
        return isoAlphaDeux;
    }

    public void setIsoAlphaDeux(IsoAlphaDeux isoAlphaDeux) {
        this.isoAlphaDeux = isoAlphaDeux;
    }

    public IsoAlphaTrois getIsoAlphaTrois() {
        return isoAlphaTrois;
    }

    public void setIsoAlphaTrois(IsoAlphaTrois isoAlphaTrois) {
        this.isoAlphaTrois = isoAlphaTrois;
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

        Pays pays = (Pays) o;

        return idPays != null ? idPays.equals(pays.idPays) : pays.idPays == null;
    }

    @Override
    public int hashCode() {
        return idPays != null ? idPays.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "idPays='" + idPays + '\'' +
                ", libellePays='" + libellePays + '\'' +
                '}';
    }
}
