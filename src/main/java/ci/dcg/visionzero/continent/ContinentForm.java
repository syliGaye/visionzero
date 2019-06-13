package ci.dcg.visionzero.continent;

public class ContinentForm {
    private String idContinent;
    private String libelleContinent;

    public ContinentForm() {
        super();
    }

    public ContinentForm(String idContinent, String libelleContinent) {
        this.idContinent = idContinent;
        this.libelleContinent = libelleContinent;
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

    public Continent createNewContinent(){
        return new Continent(getLibelleContinent());
    }

    public Continent updateContinent(){
        return new Continent(getIdContinent(), getLibelleContinent());
    }
}
