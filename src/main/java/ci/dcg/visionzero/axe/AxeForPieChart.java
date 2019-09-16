package ci.dcg.visionzero.axe;

public class AxeForPieChart {
    private String libelleAxe;
    private Double ValeurNote;
    private String hexCouleur;

    public AxeForPieChart() {
        super();
    }

    public String getLibelleAxe() {
        return libelleAxe;
    }

    public void setLibelleAxe(String libelleAxe) {
        this.libelleAxe = libelleAxe;
    }

    public Double getValeurNote() {
        return ValeurNote;
    }

    public void setValeurNote(Double valeurNote) {
        ValeurNote = valeurNote;
    }

    public String getHexCouleur() {
        return hexCouleur;
    }

    public void setHexCouleur(String hexCouleur) {
        this.hexCouleur = hexCouleur;
    }
}
