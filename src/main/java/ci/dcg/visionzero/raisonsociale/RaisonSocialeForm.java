package ci.dcg.visionzero.raisonsociale;

public class RaisonSocialeForm {
    private String idRaisonSociale;
    private String libelleRaisonSociale;

    public RaisonSocialeForm() {
        super();
    }

    public RaisonSocialeForm(String idRaisonSociale, String libelleRaisonSociale) {
        this.idRaisonSociale = idRaisonSociale;
        this.libelleRaisonSociale = libelleRaisonSociale;
    }

    public String getIdRaisonSociale() {
        return idRaisonSociale;
    }

    public void setIdRaisonSociale(String idRaisonSociale) {
        this.idRaisonSociale = idRaisonSociale;
    }

    public String getLibelleRaisonSociale() {
        return libelleRaisonSociale;
    }

    public void setLibelleRaisonSociale(String libelleRaisonSociale) {
        this.libelleRaisonSociale = libelleRaisonSociale;
    }

    public RaisonSociale createNewRaisonSociale(){
        return new RaisonSociale(getLibelleRaisonSociale());
    }
}
