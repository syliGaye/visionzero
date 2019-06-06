package ci.dcg.visionzero.notationquestion;

import ci.dcg.visionzero.question.Questionnaire;
import ci.dcg.visionzero.reponse.Reponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationQuestion implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "CODE_NOTATION_QUESTIONNAIRE", updatable = false, nullable = false)
    private String codeNotationQuestionnaire;


    @JsonManagedReference
    @JoinColumn(name = "CODE_QUESTIONNAIRE", referencedColumnName = "CODE_QUESTIONNAIRE")
    @OneToOne(optional = false)
    private Questionnaire questionnaire;

    @JsonManagedReference
    @JoinColumn(name = "CODE_REPONSE", referencedColumnName = "CODE_REPONSE")
    @ManyToOne(optional = false)
    private Reponse reponse;

    public NotationQuestion() {
        super();
    }

    public NotationQuestion(String codeNotationQuestionnaire) {
        this.codeNotationQuestionnaire = codeNotationQuestionnaire;
    }

    public String getCodeNotationQuestionnaire() {
        return codeNotationQuestionnaire;
    }

    public void setCodeNotationQuestionnaire(String codeNotationQuestionnaire) {
        this.codeNotationQuestionnaire = codeNotationQuestionnaire;
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

        NotationQuestion that = (NotationQuestion) o;

        return codeNotationQuestionnaire != null ? codeNotationQuestionnaire.equals(that.codeNotationQuestionnaire) : that.codeNotationQuestionnaire == null;
    }

    @Override
    public int hashCode() {
        return codeNotationQuestionnaire != null ? codeNotationQuestionnaire.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotationQuestion{" +
                "codeNotationQuestionnaire='" + codeNotationQuestionnaire + '\'' +
                '}';
    }
}
