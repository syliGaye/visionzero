package ci.dcg.visionzero.couleur;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.reponse.Reponse;

public class CouleurOneList {
    private String codeCouleur;
    private String libelleCouleur;
    private String rgbCouleur;
    private String hexCouleur;
    private Reponse reponse;
    private Axe axe;

    public CouleurOneList() {
        super();
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getLibelleCouleur() {
        return libelleCouleur;
    }

    public void setLibelleCouleur(String libelleCouleur) {
        this.libelleCouleur = libelleCouleur;
    }

    public String getRgbCouleur() {
        return rgbCouleur;
    }

    public void setRgbCouleur(String rgbCouleur) {
        this.rgbCouleur = rgbCouleur;
    }

    public String getHexCouleur() {
        return hexCouleur;
    }

    public void setHexCouleur(String hexCouleur) {
        this.hexCouleur = hexCouleur;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }
}
