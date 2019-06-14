package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.pays.Pays;

public class IsoAlphaTroisForm {
    private String idIsoAlphaTrois;
    private String codeIsoAlphaTrois;
    private Pays pays;

    public IsoAlphaTroisForm(String codeIsoAlphaTrois, Pays pays) {
        this.codeIsoAlphaTrois = codeIsoAlphaTrois;
        this.pays = pays;
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

    public IsoAlphaTrois createNewIsoAlphaTrois(){
        return new IsoAlphaTrois(getCodeIsoAlphaTrois(), getPays());
    }
}
