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
    @Column(name = "CODE_NOTATION_AXE")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotationAxe that = (NotationAxe) o;

        if (codeNotationAxe != null ? !codeNotationAxe.equals(that.codeNotationAxe) : that.codeNotationAxe != null)
            return false;
        if (valeurNotationAxe != null ? !valeurNotationAxe.equals(that.valeurNotationAxe) : that.valeurNotationAxe != null)
            return false;
        if (axe != null ? !axe.equals(that.axe) : that.axe != null) return false;
        return entreprise != null ? entreprise.equals(that.entreprise) : that.entreprise == null;
    }

    @Override
    public int hashCode() {
        int result = codeNotationAxe != null ? codeNotationAxe.hashCode() : 0;
        result = 31 * result + (valeurNotationAxe != null ? valeurNotationAxe.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NotationAxe{" +
                "codeNotationAxe=" + codeNotationAxe +
                ", valeurNotationAxe=" + valeurNotationAxe +
                ", axe=" + axe +
                ", entreprise=" + entreprise +
                '}';
    }
}
