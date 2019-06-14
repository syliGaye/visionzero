package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.pays.Pays;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class IsoAlphaDeux implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_ISOALPHA_DEUX", updatable = false, nullable = false)
    private String idIsoAlphaDeux;

    @Basic(optional = false)
    @NotNull
    @Size(max = 2)
    @Column(name = "LIB_ISOALPHA_DEUX", unique = true, nullable = false)
    private String codeIsoAlphaDeux;

    @JsonManagedReference
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @OneToOne(optional = false)
    private Pays pays;

    public IsoAlphaDeux() {
        super();
    }

    public IsoAlphaDeux(String idIsoAlpha2) {
        this.idIsoAlphaDeux = idIsoAlpha2;
    }

    public IsoAlphaDeux(String idIsoAlphaDeux, String codeIsoAlphaDeux) {
        this.idIsoAlphaDeux = idIsoAlphaDeux;
        this.codeIsoAlphaDeux = codeIsoAlphaDeux;
    }

    public IsoAlphaDeux(String codeIsoAlphaDeux, Pays pays) {
        this.pays = pays;
        this.codeIsoAlphaDeux = codeIsoAlphaDeux;
    }

    public String getIdIsoAlphaDeux() {
        return idIsoAlphaDeux;
    }

    public void setIdIsoAlphaDeux(String idIsoAlphaDeux) {
        this.idIsoAlphaDeux = idIsoAlphaDeux;
    }

    public String getCodeIsoAlphaDeux() {
        return codeIsoAlphaDeux;
    }

    public void setCodeIsoAlphaDeux(String codeIsoAlphaDeux) {
        this.codeIsoAlphaDeux = codeIsoAlphaDeux;
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

        IsoAlphaDeux isoAlphaDeux = (IsoAlphaDeux) o;

        return idIsoAlphaDeux != null ? idIsoAlphaDeux.equals(isoAlphaDeux.idIsoAlphaDeux) : isoAlphaDeux.idIsoAlphaDeux == null;
    }

    @Override
    public int hashCode() {
        return idIsoAlphaDeux != null ? idIsoAlphaDeux.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IsoAlphaDeux{" +
                "idIsoAlphaDeux='" + idIsoAlphaDeux + '\'' +
                ", codeIsoAlphaDeux='" + codeIsoAlphaDeux + '\'' +
                '}';
    }
}
