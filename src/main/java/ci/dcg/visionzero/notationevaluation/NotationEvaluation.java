package ci.dcg.visionzero.notationevaluation;

import ci.dcg.visionzero.evaluation.Evaluation;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_EVALUATION", updatable = false, nullable = false)
    private String codeNotationEvaluation;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALEUR_NOTATION_EVALUATION", nullable = false)
    private Double valeurNotationEvaluation;

    @JsonManagedReference
    @JoinColumn(name = "CODE_EVALUATION", referencedColumnName = "CODE_EVALUATION")
    @OneToOne(optional = false)
    private Evaluation evaluation;

    public NotationEvaluation() {
    }

    public NotationEvaluation(String codeNotationEvaluation) {
        this.codeNotationEvaluation = codeNotationEvaluation;
    }

    public NotationEvaluation(String codeNotationEvaluation, Double valeurNotationEvaluation) {
        this.codeNotationEvaluation = codeNotationEvaluation;
        this.valeurNotationEvaluation = valeurNotationEvaluation;
    }

    public String getCodeNotationEvaluation() {
        return codeNotationEvaluation;
    }

    public void setCodeNotationEvaluation(String codeNotationEvaluation) {
        this.codeNotationEvaluation = codeNotationEvaluation;
    }

    public Double getValeurNotationEvaluation() {
        return valeurNotationEvaluation;
    }

    public void setValeurNotationEvaluation(Double valeurNotationEvaluation) {
        this.valeurNotationEvaluation = valeurNotationEvaluation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeNotationEvaluation != null ? codeNotationEvaluation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotationEvaluation)) {
            return false;
        }
        NotationEvaluation other = (NotationEvaluation) object;
        if ((this.codeNotationEvaluation == null && other.codeNotationEvaluation != null) || (this.codeNotationEvaluation != null && !this.codeNotationEvaluation.equals(other.codeNotationEvaluation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotationEvaluation{" +
                "codeNotationEvaluation='" + codeNotationEvaluation + '\'' +
                ", valeurNotationEvaluation=" + valeurNotationEvaluation +
                //", evaluation=" + evaluation +
                '}';
    }
}
