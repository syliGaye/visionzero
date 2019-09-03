package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.pays.Pays;
import ci.dcg.visionzero.raisonsociale.RaisonSociale;
import ci.dcg.visionzero.secteuractivite.SecteurActivite;

public class EntrepriseOneList {
    private String codeEntreprise;
    private String nomEntreprise;
    private String descriptionEntreprise;
    private Pays pays;
    private SecteurActivite secteurActivite;
    private RaisonSociale raisonSociale;

    public EntrepriseOneList() {
        super();
    }

    public String getCodeEntreprise() {
        return codeEntreprise;
    }

    public void setCodeEntreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getDescriptionEntreprise() {
        return descriptionEntreprise;
    }

    public void setDescriptionEntreprise(String descriptionEntreprise) {
        this.descriptionEntreprise = descriptionEntreprise;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public SecteurActivite getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public RaisonSociale getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(RaisonSociale raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

}