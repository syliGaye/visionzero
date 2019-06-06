package ci.dcg.visionzero.notationevaluation;

import ci.dcg.visionzero.evaluation.Evaluation;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationEvaluationInd implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_EVALUATION_IND", updatable = false, nullable = false)
    private String codeNotationEvaluationInd;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALEUR_NOTATION_EVALUATION_IND", nullable = false)
    private Double valeurNotationEvaluationInd;

    @JsonManagedReference
    @JoinColumn(name = "CODE_EVALUATION", referencedColumnName = "CODE_EVALUATION")
    @OneToOne(optional = false)
    private Evaluation evaluation;

    public NotationEvaluationInd() {
        super();
    }

    public NotationEvaluationInd(String codeNotationEvaluationInd, Double valeurNotationEvaluationInd) {
        this.codeNotationEvaluationInd = codeNotationEvaluationInd;
        this.valeurNotationEvaluationInd = valeurNotationEvaluationInd;
    }

    public NotationEvaluationInd(String codeNotationEvaluationInd, Double valeurNotationEvaluationInd, Evaluation evaluation) {
        this.codeNotationEvaluationInd = codeNotationEvaluationInd;
        this.valeurNotationEvaluationInd = valeurNotationEvaluationInd;
        this.evaluation = evaluation;
    }

    public String getCodeNotationEvaluationInd() {
        return codeNotationEvaluationInd;
    }

    public void setCodeNotationEvaluationInd(String codeNotationEvaluationInd) {
        this.codeNotationEvaluationInd = codeNotationEvaluationInd;
    }

    public Double getValeurNotationEvaluationInd() {
        return valeurNotationEvaluationInd;
    }

    public void setValeurNotationEvaluationInd(Double valeurNotationEvaluationInd) {
        this.valeurNotationEvaluationInd = valeurNotationEvaluationInd;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotationEvaluationInd that = (NotationEvaluationInd) o;

        return codeNotationEvaluationInd != null ? codeNotationEvaluationInd.equals(that.codeNotationEvaluationInd) : that.codeNotationEvaluationInd == null;
    }

    @Override
    public int hashCode() {
        return codeNotationEvaluationInd != null ? codeNotationEvaluationInd.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotationEvaluationInd{" +
                "codeNotationEvaluationInd='" + codeNotationEvaluationInd + '\'' +
                ", valeurNotationEvaluationInd=" + valeurNotationEvaluationInd +
                '}';
    }
}
