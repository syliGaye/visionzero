package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.pays.Pays;

public class IsoAlphaTroisForm {
    private String idIsoAlphaTrois;
    private String codeIsoAlphaTrois;
    private Pays pays;
    private String idPays;
    private String etat;

    public IsoAlphaTroisForm() {
        super();
    }

    public IsoAlphaTroisForm(String idIsoAlphaTrois, String codeIsoAlphaTrois, String idPays) {
        this.idIsoAlphaTrois = idIsoAlphaTrois;
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
        this.idPays = idPays;
    }

    public String getIdIsoAlphaTrois() {
        return idIsoAlphaTrois;
    }

    public void setIdIsoAlphaTrois(String idIsoAlphaTrois) {
        this.idIsoAlphaTrois = idIsoAlphaTrois;
    }

    public String getCodeIsoAlphaTrois() {
        return codeIsoAlphaTrois;
    }

    public void setCodeIsoAlphaTrois(String codeIsoAlphaTrois) {
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public String getIdPays() {
        return idPays;
    }

    public void setIdPays(String idPays) {
        this.idPays = idPays;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public IsoAlphaTrois createNewIsoAlphaTrois(){
        return new IsoAlphaTrois(getCodeIsoAlphaTrois(), getPays());
    }
}
