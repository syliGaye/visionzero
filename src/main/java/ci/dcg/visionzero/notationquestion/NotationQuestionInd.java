package ci.dcg.visionzero.notationquestion;

import ci.dcg.visionzero.question.Questionnaire;
import ci.dcg.visionzero.reponse.Reponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationQuestionInd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_QUESTION_IND", updatable = false, nullable = false)
    private String codNotationQuestionInd;

    @JsonManagedReference
    @JoinColumn(name = "CODE_QUESTIONNAIRE", referencedColumnName = "CODE_QUESTIONNAIRE")
    @OneToOne(optional = false)
    private Questionnaire questionnaire;

    @JsonManagedReference
    @JoinColumn(name = "CODE_REPONSE", referencedColumnName = "CODE_REPONSE")
    @ManyToOne(optional = false)
    private Reponse reponse;

    public NotationQuestionInd() {
        super();
    }

    public NotationQuestionInd(String codNotationQuestionInd) {
        this.codNotationQuestionInd = codNotationQuestionInd;
    }

    public String getCodNotationQuestionInd() {
        return codNotationQuestionInd;
    }

    public void setCodNotationQuestionInd(String codNotationQuestionInd) {
        this.codNotationQuestionInd = codNotationQuestionInd;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotationQuestionInd that = (NotationQuestionInd) o;

        return codNotationQuestionInd != null ? codNotationQuestionInd.equals(that.codNotationQuestionInd) : that.codNotationQuestionInd == null;
    }

    @Override
    public int hashCode() {
        return codNotationQuestionInd != null ? codNotationQuestionInd.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotationQuestionInd{" +
                "codNotationQuestionInd='" + codNotationQuestionInd + '\'' +
                '}';
    }
}
