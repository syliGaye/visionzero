package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.pays.Pays;
import ci.dcg.visionzero.raisonsociale.RaisonSociale;
import ci.dcg.visionzero.secteuractivite.SecteurActivite;

public class EntrepriseForm {
    private String codeEntreprise;
    private String nomEntreprise;
    private String descriptionEntreprise;
    private String idPays;
    private String idSecteurActivite;
    private String idRaisonSociale;
    private String etat;
    private Pays pays;
    private SecteurActivite secteurActivite;
    private RaisonSociale raisonSociale;

    public EntrepriseForm() {
        super();
    }

    public EntrepriseForm(String codeEntreprise, String nomEntreprise, String descriptionEntreprise, String idPays, String idSecteurActivite, String idRaisonSociale) {
        this.codeEntreprise = codeEntreprise;
        this.nomEntreprise = nomEntreprise;
        this.descriptionEntreprise = descriptionEntreprise;
        this.idPays = idPays;
        this.idSecteurActivite = idSecteurActivite;
        this.idRaisonSociale = idRaisonSociale;
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

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    public String getIdSecteurActivite() {
        return idSecteurActivite;
    }

    public void setIdSecteurActivite(String idSecteurActivite) {
        this.idSecteurActivite = idSecteurActivite;
    }

    public String getIdRaisonSociale() {
        return idRaisonSociale;
    }

    public void setIdRaisonSociale(String idRaisonSociale) {
        this.idRaisonSociale = idRaisonSociale;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public Entreprise createNewEntreprise(){
        return new Entreprise(getNomEntreprise(), getDescriptionEntreprise(), getPays(), getSecteurActivite(), getRaisonSociale());
    }
}
