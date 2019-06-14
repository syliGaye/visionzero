package ci.dcg.visionzero.secteuractivite;

public class SecteurActiviteForm {

    private String idSecteurActivite;
    private String libelleSecteurActivite;

    public SecteurActiviteForm() {
        super();
    }

    public SecteurActiviteForm(String idSecteurActivite, String libelleSecteurActivite) {
        this.idSecteurActivite = idSecteurActivite;
        this.libelleSecteurActivite = libelleSecteurActivite;
    }

    public String getIdSecteurActivite() {
        return idSecteurActivite;
    }

    public void setIdSecteurActivite(String idSecteurActivite) {
        this.idSecteurActivite = idSecteurActivite;
    }

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
