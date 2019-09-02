package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.evaluation.EvaluationOneList;
import ci.dcg.visionzero.imageuser.ImageUser;

import java.util.List;

public class AxeOneList {
    private String codeAxe;
    private String libelleAxe;
    private String descriptionAxe;
    private Couleur couleur;
    private ImageUser imageUser;
    private List<EvaluationOneList> evaluationOneLists;

    public AxeOneList() {
        super();
    }

    public String getCodeAxe() {
        return codeAxe;
    }

    public void setCodeAxe(String codeAxe) {
        this.codeAxe = codeAxe;
    }

    public String getLibelleAxe() {
        return libelleAxe;
    }

    public void setLibelleAxe(String libelleAxe) {
        this.libelleAxe = libelleAxe;
    }

    public String getDescriptionAxe() {
        return descriptionAxe;
    }

    public void setDescriptionAxe(String descriptionAxe) {
        this.descriptionAxe = descriptionAxe;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public ImageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageUser imageUser) {
        this.imageUser = imageUser;
    }

    public List<EvaluationOneList> getEvaluationOneLists() {
        return evaluationOneLists;
    }

    public void setEvaluationOneLists(List<EvaluationOneList> evaluationOneLists) {
        this.evaluationOneLists = evaluationOneLists;
    }
}
