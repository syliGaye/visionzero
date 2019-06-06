package ci.dcg.visionzero.notationaxe;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.entreprise.Entreprise;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationAxeInd implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_AXE_IND", updatable = false, nullable = false)
    private String codeNotationAxeInd;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALEUR_NOTATION_AXE_IND", nullable = false)
    private Double valeurNotationAxeInd;

    @JsonManagedReference
    @JoinColumn(name = "CODE_AXE", referencedColumnName = "CODE_AXE")
    @ManyToOne(optional = false)
    private Axe axe;

    @JsonManagedReference
    @JoinColumn(name = "CODE_ENTREPRISE", referencedColumnName = "CODE_ENTREPRISE")
    @ManyToOne(optional = false)
    private Entreprise entreprise;

    public NotationAxeInd() {
        super();
    }

    public NotationAxeInd(String codeNotationAxeInd, Double valeurNotationAxeInd) {
        this.codeNotationAxeInd = codeNotationAxeInd;
        this.valeurNotationAxeInd = valeurNotationAxeInd;
    }

    public NotationAxeInd(Double valeurNotationAxeInd, Axe axe, Entreprise entreprise) {
        this.valeurNotationAxeInd = valeurNotationAxeInd;
        this.axe = axe;
        this.entreprise = entreprise;
    }

    public NotationAxeInd(String codeNotationAxeInd) {
        this.codeNotationAxeInd = codeNotationAxeInd;
    }

    public NotationAxeInd(String codeNotationAxeInd, Double valeurNotationAxeInd, Axe axe) {
        this.codeNotationAxeInd = codeNotationAxeInd;
        this.valeurNotationAxeInd = valeurNotationAxeInd;
        this.axe = axe;
    }

    public String getCodeNotationAxeInd() {
        return codeNotationAxeInd;
    }

    public void setCodeNotationAxeInd(String codeNotationAxeInd) {
        this.codeNotationAxeInd = codeNotationAxeInd;
    }

    public Double getValeurNotationAxeInd() {
        return valeurNotationAxeInd;
    }

    public void setValeurNotationAxeInd(Double valeurNotationAxeInd) {
        this.valeurNotationAxeInd = valeurNotationAxeInd;
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

        NotationAxeInd that = (NotationAxeInd) o;

        return codeNotationAxeInd != null ? codeNotationAxeInd.equals(that.codeNotationAxeInd) : that.codeNotationAxeInd == null;
    }

    @Override
    public int hashCode() {
        return codeNotationAxeInd != null ? codeNotationAxeInd.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotationAxeInd{" +
                "codeNotationAxeInd='" + codeNotationAxeInd + '\'' +
                ", valeurNotationAxeInd=" + valeurNotationAxeInd +
                '}';
    }
}
