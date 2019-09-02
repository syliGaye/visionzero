package ci.dcg.visionzero.continent;

import ci.dcg.visionzero.pays.Pays;

import java.util.List;

public class ContinentOneList {
    private String idContinent;
    private String libelleContinent;
    private List<Pays> paysList;

    public ContinentOneList() {
        super();
    }

    public String getIdContinent() {
        return idContinent;
    }

    public void setIdContinent(String idContinent) {
        this.idContinent = idContinent;
    }

    public String getLibelleContinent() {
        return libelleContinent;
    }

    public void setLibelleContinent(String libelleContinent) {
        this.libelleContinent = libelleContinent;
    }

    public List<Pays> getPaysList() {
        return paysList;
    }

    public void setPaysList(List<Pays> paysList) {
        this.paysList = paysList;
    }
}
