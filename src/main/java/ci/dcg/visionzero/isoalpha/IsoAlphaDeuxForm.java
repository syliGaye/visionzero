package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.pays.Pays;

public class IsoAlphaDeuxForm {
    private String idIsoAlphaDeux;
    private String codeIsoAlphaDeux;
    private Pays pays;

    public IsoAlphaDeuxForm(String codeIsoAlphaDeux, Pays pays) {
        this.codeIsoAlphaDeux = codeIsoAlphaDeux;
        this.pays = pays;
    }

    public String getIdIsoAlphaDeux() {
        return idIsoAlphaDeux;
    }

    public void setIdIsoAlphaDeux(String idIsoAlphaDeux) {
        this.idIsoAlphaDeux = idIsoAlphaDeux;
    }

    public String getCodeIsoAlphaDeux() {
        return codeIsoAlphaDeux;
    }

    public void setCodeIsoAlphaDeux(String codeIsoAlphaDeux) {
        this.codeIsoAlphaDeux = codeIsoAlphaDeux;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public IsoAlphaDeux createNewIsoAlphaDeux(){
        return new IsoAlphaDeux(getCodeIsoAlphaDeux(), getPays());
    }
}
