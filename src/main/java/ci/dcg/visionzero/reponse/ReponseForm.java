package ci.dcg.visionzero.reponse;

import ci.dcg.visionzero.couleur.Couleur;

public class ReponseForm {
    private String codeReponse;
    private String libelleReponse;
    private Integer valeurReponse;
    private String idCouleur;
    private String etat;
    private Couleur couleur;

    public ReponseForm() {
        super();
    }

    public ReponseForm(String codeReponse, String libelleReponse, Integer valeurReponse, String codeCouleur) {
        this.codeReponse = codeReponse;
        this.libelleReponse = libelleReponse;
        this.valeurReponse = valeurReponse;
        this.idCouleur = codeCouleur;
    }

    public String getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(String codeReponse) {
        this.codeReponse = codeReponse;
    }

    public String getLibelleReponse() {
        return libelleReponse;
    }

    public void setLibelleReponse(String libelleReponse) {
        this.libelleReponse = libelleReponse;
    }

    public Integer getValeurReponse() {
        return valeurReponse;
    }

    public void setValeurReponse(Integer valeurReponse) {
        this.valeurReponse = valeurReponse;
    }

    public String getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(String idCouleur) {
        this.idCouleur = idCouleur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Reponse createNewReponse(){
        return new Reponse(getLibelleReponse(), getValeurReponse(), getCouleur());
    }
}
