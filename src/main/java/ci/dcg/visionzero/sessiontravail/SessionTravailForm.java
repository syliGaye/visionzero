package ci.dcg.visionzero.sessiontravail;

public class SessionTravailForm {

    private String libelleSession;

    public String getLibelleSession() {
        return libelleSession;
    }

    public void setLibelleSession(String libelleSession) {
        this.libelleSession = libelleSession;
    }

    public SessionTravail createNewSessionTravail(){
        return new SessionTravail(getLibelleSession());
    }
}
