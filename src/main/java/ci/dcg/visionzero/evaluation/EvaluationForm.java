package ci.dcg.visionzero.evaluation;

import ci.dcg.visionzero.axe.Axe;

public class EvaluationForm {

    private String codeEvaluation;
    private String libelleEvaluation;
    private String idAxe;
    private Axe axe;
    private String etat;

    public EvaluationForm() {
        super();
    }

    public EvaluationForm(String codeEvaluation, String libelleEvaluation, String idAxe) {
        this.codeEvaluation = codeEvaluation;
        this.libelleEvaluation = libelleEvaluation;
        this.idAxe = idAxe;
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

    public String getIdAxe() {
        return idAxe;
    }

    public void setIdAxe(String idAxe) {
        this.idAxe = idAxe;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Evaluation createNewEvaluation(){
        return new Evaluation(getLibelleEvaluation(), getAxe());
    }
}
