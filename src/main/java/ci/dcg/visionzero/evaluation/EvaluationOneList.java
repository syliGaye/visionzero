package ci.dcg.visionzero.evaluation;

import ci.dcg.visionzero.question.QuestionnaireOneList;

import java.util.List;

public class EvaluationOneList {
    private String codeEvaluation;
    private String libelleEvaluation;
    private List<QuestionnaireOneList> questionnaireOneLists;

    public EvaluationOneList() {
        super();
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

    public List<QuestionnaireOneList> getQuestionnaireOneLists() {
        return questionnaireOneLists;
    }

    public void setQuestionnaireOneLists(List<QuestionnaireOneList> questionnaireOneLists) {
        this.questionnaireOneLists = questionnaireOneLists;
    }
}
