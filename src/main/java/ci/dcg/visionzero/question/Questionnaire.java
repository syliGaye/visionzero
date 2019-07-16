package ci.dcg.visionzero.question;

import ci.dcg.visionzero.evaluation.Evaluation;
import ci.dcg.visionzero.notationquestion.NotationQuestion;
import ci.dcg.visionzero.notationquestion.NotationQuestionInd;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_QUESTIONNAIRE", nullable = false, updatable = false)
    private String codeQuestionnaire;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "LIBELLE_QUESTIONNAIRE", unique = true, nullable = false)
    private String libelleQuestionnaire;

    @JsonManagedReference
    @JoinColumn(name = "CODE_EVALUATION", referencedColumnName = "CODE_EVALUATION")
    @ManyToOne(optional = false)
    private Evaluation evaluation;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    private NotationQuestion notationQuestion;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "questionnaire")
    private NotationQuestionInd notationQuestionInd;

    public Questionnaire() {
    }

    public Questionnaire(String codeQuestionnaire) {
        this.codeQuestionnaire = codeQuestionnaire;
    }

    public Questionnaire(String codeQuestionnaire, String libelleQuestionnaire) {
        this.codeQuestionnaire = codeQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
    }

    public Questionnaire(String libelleQuestionnaire, Evaluation evaluation) {
        this.evaluation = evaluation;
        this.libelleQuestionnaire = libelleQuestionnaire;
    }

    public String getCodeQuestionnaire() {
        return codeQuestionnaire;
    }

    public void setCodeQuestionnaire(String codeQuestionnaire) {
        this.codeQuestionnaire = codeQuestionnaire;
    }

    public String getLibelleQuestionnaire() {
        return libelleQuestionnaire;
    }

    public void setLibelleQuestionnaire(String libelleQuestionnaire) {
        this.libelleQuestionnaire = libelleQuestionnaire;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    @XmlTransient
    public NotationQuestion getNotationQuestion() {
        return notationQuestion;
    }

    public void setNotationQuestion(NotationQuestion notationQuestion) {
        this.notationQuestion = notationQuestion;
    }

    @XmlTransient
    public NotationQuestionInd getNotationQuestionInd() {
        return notationQuestionInd;
    }

    public void setNotationQuestionInd(NotationQuestionInd notationQuestionInd) {
        this.notationQuestionInd = notationQuestionInd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeQuestionnaire != null ? codeQuestionnaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionnaire)) {
            return false;
        }
        Questionnaire other = (Questionnaire) object;
        if ((this.codeQuestionnaire == null && other.codeQuestionnaire != null) || (this.codeQuestionnaire != null && !this.codeQuestionnaire.equals(other.codeQuestionnaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "codeQuestionnaire='" + codeQuestionnaire + '\'' +
                ", libelleQuestionnaire='" + libelleQuestionnaire + '\'' +
                ", notationQuestion=" + notationQuestion +
                ", notationQuestionInd=" + notationQuestionInd +
                '}';
    }
}
