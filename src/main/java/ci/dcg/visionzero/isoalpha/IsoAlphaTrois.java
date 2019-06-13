package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.pays.Pays;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class IsoAlphaTrois implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_ISOALPHA_TROIS",nullable = false, updatable = false)
    private String idIsoAlphaTrois;

    @Basic(optional = false)
    @NotNull
    @Size(min = 3)
    @Column(name = "LIB_ISOALPHA_TROIS", unique = true, nullable = false)
    private String codeIsoAlphaTrois;

    @JsonManagedReference
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @OneToOne(optional = false)
    private Pays pays;

    public IsoAlphaTrois() {
        super();
    }

    public IsoAlphaTrois(String idIsoAlphaTrois) {
        this.idIsoAlphaTrois = idIsoAlphaTrois;
    }

    public IsoAlphaTrois(String idIsoAlphaTrois, String codeIsoAlphaTrois) {
        this.idIsoAlphaTrois = idIsoAlphaTrois;
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
    }

    public IsoAlphaTrois(String codeIsoAlphaTrois, Pays pays) {
        this.pays = pays;
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
    }

    public String getIdIsoAlphaTrois() {
        return idIsoAlphaTrois;
    }

    public void setIdIsoAlphaTrois(String idIsoAlphaTrois) {
        this.idIsoAlphaTrois = idIsoAlphaTrois;
    }

    public String getCodeIsoAlphaTrois() {
        return codeIsoAlphaTrois;
    }

    public void setCodeIsoAlphaTrois(String codeIsoAlphaTrois) {
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IsoAlphaTrois isoAlphaTrois = (IsoAlphaTrois) o;

        return idIsoAlphaTrois != null ? idIsoAlphaTrois.equals(isoAlphaTrois.idIsoAlphaTrois) : isoAlphaTrois.idIsoAlphaTrois == null;
    }

    @Override
    public int hashCode() {
        return idIsoAlphaTrois != null ? idIsoAlphaTrois.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IsoAlphaTrois{" +
                "idIsoAlphaTrois='" + idIsoAlphaTrois + '\'' +
                ", codeIsoAlphaTrois='" + codeIsoAlphaTrois + '\'' +
                '}';
    }
}
