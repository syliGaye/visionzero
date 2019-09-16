package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.notationaxe.NotationAxe;

public class EntrepriseForLineChart {
    private String nomEntreprise;
    private String couleur;
    private Double notationAxe;

    public EntrepriseForLineChart() {
        super();
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public Double getNotationAxe() {
        return notationAxe;
    }

    public void setNotationAxe(Double notationAxe) {
        this.notationAxe = notationAxe;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
