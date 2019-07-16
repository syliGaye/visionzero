package ci.dcg.visionzero.question;

import ci.dcg.visionzero.evaluation.Evaluation;

public class QuestionnaireForm {
    private String codeQuestionnaire;
    private String libelleQuestionnaire;
    private String codeEvaluation;
    private Evaluation evaluation;
    private String etat;

    public QuestionnaireForm() {
        super();
    }

    public QuestionnaireForm(String codeQuestionnaire, String libelleQuestionnaire, String codeEvaluation) {
        this.codeQuestionnaire = codeQuestionnaire;
        this.libelleQuestionnaire = libelleQuestionnaire;
        this.codeEvaluation = codeEvaluation;
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

    public String getCodeEvaluation() {
        return codeEvaluation;
    }

    public void setCodeEvaluation(String codeEvaluation) {
        this.codeEvaluation = codeEvaluation;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Questionnaire createNewQuestionnaire() {
        return new Questionnaire(getLibelleQuestionnaire(), getEvaluation());
    }
}
