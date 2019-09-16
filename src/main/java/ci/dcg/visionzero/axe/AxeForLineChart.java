package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.entreprise.EntrepriseForLineChart;

import java.util.List;

public class AxeForLineChart {
    private String libelleAxe;
    private List<EntrepriseForLineChart> entrepriseForLineCharts;

    public AxeForLineChart() {
        super();
    }

    public String getLibelleAxe() {
        return libelleAxe;
    }

    public void setLibelleAxe(String libelleAxe) {
        this.libelleAxe = libelleAxe;
    }

    public List<EntrepriseForLineChart> getEntrepriseForLineCharts() {
        return entrepriseForLineCharts;
    }

    public void setEntrepriseForLineCharts(List<EntrepriseForLineChart> entrepriseForLineCharts) {
        this.entrepriseForLineCharts = entrepriseForLineCharts;
    }
}
