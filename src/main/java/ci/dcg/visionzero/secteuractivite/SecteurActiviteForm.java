package ci.dcg.visionzero.secteuractivite;

public class SecteurActiviteForm {

    private String libelleSecteurActivite;

    public String getLibelleSecteurActivite() {
        return libelleSecteurActivite;
    }

    public void setLibelleSecteurActivite(String libelleSecteurActivite) {
        this.libelleSecteurActivite = libelleSecteurActivite;
    }

    public SecteurActivite createNewSecteurActivite(){
        return new SecteurActivite(getLibelleSecteurActivite());
    }
}
