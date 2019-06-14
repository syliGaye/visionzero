package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.continent.Continent;

public class PaysForm {
    private String id;
    private String libellePays;
    private String idContinent;
    private String isoAlphaDeux;
    private String isoAlphaTrois;
    private Continent continent;
    private String etat;

    public PaysForm() {
        super();
    }

    public PaysForm(String id, String libellePays, String isoAlphaDeux, String isoAlphaTrois, String idContinent) {
        this.id = id;
        this.libellePays = libellePays;
        this.isoAlphaDeux = isoAlphaDeux;
        this.isoAlphaTrois = isoAlphaTrois;
        this.idContinent = idContinent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibellePays() {
        return libellePays;
    }

    public void setLibellePays(String libellePays) {
        this.libellePays = libellePays;
    }

    public String getIdContinent() {
        return idContinent;
    }

    public void setIdContinent(String idContinent) {
        this.idContinent = idContinent;
    }

    public String getIsoAlphaDeux() {
        return isoAlphaDeux;
    }

    public void setIsoAlphaDeux(String isoAlphaDeux) {
        this.isoAlphaDeux = isoAlphaDeux;
    }

    public String getIsoAlphaTrois() {
        return isoAlphaTrois;
    }

    public void setIsoAlphaTrois(String isoAlphaTrois) {
        this.isoAlphaTrois = isoAlphaTrois;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Pays createNewPays(){
        return new Pays(getLibellePays(), getContinent());
    }

    @Override
    public String toString() {
        return "PaysForm{" +
                "id='" + id + '\'' +
                ", libellePays='" + libellePays + '\'' +
                ", idContinent='" + idContinent + '\'' +
                ", isoAlphaDeux='" + isoAlphaDeux + '\'' +
                ", isoAlphaTrois='" + isoAlphaTrois + '\'' +
                ", continent=" + continent +
                ", etat='" + etat + '\'' +
                '}';
    }
}
