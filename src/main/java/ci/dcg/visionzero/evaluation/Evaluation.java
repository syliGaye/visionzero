package ci.dcg.visionzero.evaluation;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.notationevaluation.NotationEvaluation;
import ci.dcg.visionzero.question.Questionnaire;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Evaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_EVALUATION", updatable = false, nullable = false)
    private String codeEvaluation;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_EVALUATION", unique = true, nullable = false)
    private String libelleEvaluation;

    @JsonManagedReference
    @JoinColumn(name = "CODE_AXE", referencedColumnName = "CODE_AXE")
    @ManyToOne(optional = false)
    private Axe axe;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private List<Questionnaire> questionnaireList;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "evaluation")
    private NotationEvaluation notationEvaluation;

    public Evaluation() {
        super();
    }

    public Evaluation(String codeEvaluation) {
        this.codeEvaluation = codeEvaluation;
    }

    public Evaluation(String codeEvaluation, String libelleEvaluation) {
        this.codeEvaluation = codeEvaluation;
        this.libelleEvaluation = libelleEvaluation;
    }

    public Evaluation(String libelleEvaluation, Axe axe) {
        this.axe = axe;
        this.libelleEvaluation = libelleEvaluation;
    }

    public String getCodeEvaluation() {
        return codeEvaluation;
    }

    public void setCodeEvaluation(String codeEvaluation) {
        this.codeEvaluation = codeEvaluation;
    }

    public String getLibelleEvaluation() {
        return libelleEvaluation;
    }

    public void setLibelleEvaluation(String libelleEvaluation) {
        this.libelleEvaluation = libelleEvaluation;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    @XmlTransient
    public List<Questionnaire> getQuestionnaireList() {
        return questionnaireList;
    }

    public void setQuestionnaireList(List<Questionnaire> questionnaireList) {
        this.questionnaireList = questionnaireList;
    }

    @XmlTransient
    public NotationEvaluation getNotationEvaluation() {
        return notationEvaluation;
    }

    public void setNotationEvaluation(NotationEvaluation notationEvaluation) {
        this.notationEvaluation = notationEvaluation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeEvaluation != null ? codeEvaluation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.codeEvaluation == null && other.codeEvaluation != null) || (this.codeEvaluation != null && !this.codeEvaluation.equals(other.codeEvaluation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "codeEvaluation='" + codeEvaluation + '\'' +
                ", libelleEvaluation='" + libelleEvaluation + '\'' +
                ", questionnaireList=" + questionnaireList +
                ", notationEvaluation=" + notationEvaluation +
                '}';
    }
}
