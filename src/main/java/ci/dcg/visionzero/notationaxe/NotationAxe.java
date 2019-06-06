package ci.dcg.visionzero.notationaxe;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.entreprise.Entreprise;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationAxe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_AXE", updatable = false, nullable = false)
    private String codeNotationAxe;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALEUR_NOTATION_AXE", nullable = false)
    private Double valeurNotationAxe;

    @JsonManagedReference
    @JoinColumn(name = "CODE_AXE", referencedColumnName = "CODE_AXE")
    @ManyToOne(optional = false)
    private Axe axe;

    @JsonManagedReference
    @JoinColumn(name = "CODE_ENTREPRISE", referencedColumnName = "CODE_ENTREPRISE")
    @ManyToOne(optional = false)
    private Entreprise entreprise;

    public NotationAxe() {
    }

    public NotationAxe(String codeNotationAxe) {
        this.codeNotationAxe = codeNotationAxe;
    }

    public NotationAxe(String codeNotationAxe, Double valeurNotationAxe) {
        this.codeNotationAxe = codeNotationAxe;
        this.valeurNotationAxe = valeurNotationAxe;
    }

    public NotationAxe(Double valeurNotationAxe, Axe axe, Entreprise entreprise) {
        this.valeurNotationAxe = valeurNotationAxe;
        this.axe = axe;
        this.entreprise = entreprise;
    }

    public String getCodeNotationAxe() {
        return codeNotationAxe;
    }

    public void setCodeNotationAxe(String codeNotationAxe) {
        this.codeNotationAxe = codeNotationAxe;
    }

    public Double getValeurNotationAxe() {
        return valeurNotationAxe;
    }

    public void setValeurNotationAxe(Double valeurNotationAxe) {
        this.valeurNotationAxe = valeurNotationAxe;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeNotationAxe != null ? codeNotationAxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotationAxe)) {
            return false;
        }
        NotationAxe other = (NotationAxe) object;
        if ((this.codeNotationAxe == null && other.codeNotationAxe != null) || (this.codeNotationAxe != null && !this.codeNotationAxe.equals(other.codeNotationAxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotationAxe{" +
                "codeNotationAxe='" + codeNotationAxe + '\'' +
                ", valeurNotationAxe=" + valeurNotationAxe +
                //", axe=" + axe +
                '}';
    }
}
