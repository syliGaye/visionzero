package ci.dcg.visionzero.notationsession;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.sessiontravail.SessionTravail;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
public class NotationSession implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotationSessionPK notationSessionPK;

    @NotNull
    @Column(name = "VALEUR_NOTATION_SESSION", updatable = false, nullable = false)
    private double valeurNotationSession;

    @JsonManagedReference
    @JoinColumn(name = "CODE_SESSION", referencedColumnName = "CODE_SESSION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SessionTravail sessionTravail;

    @JsonManagedReference
    @JoinColumn(name = "CODE_AXE", referencedColumnName = "CODE_AXE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Axe axe;

    public NotationSession() {
    }

    public NotationSession(NotationSessionPK notationSessionPK) {
        this.notationSessionPK = notationSessionPK;
    }

    public NotationSession(NotationSessionPK notationSessionPK, double valeurNotationSession) {
        this.notationSessionPK = notationSessionPK;
        this.valeurNotationSession = valeurNotationSession;
    }

    public NotationSession(String codeSession, String codeAxe) {
        this.notationSessionPK = new NotationSessionPK(codeSession, codeAxe);
    }

    public NotationSessionPK getNotationSessionPK() {
        return notationSessionPK;
    }

    public void setNotationSessionPK(NotationSessionPK notationSessionPK) {
        this.notationSessionPK = notationSessionPK;
    }

    public double getValeurNotationSession() {
        return valeurNotationSession;
    }

    public void setValeurNotationSession(double valeurNotationSession) {
        this.valeurNotationSession = valeurNotationSession;
    }

    public SessionTravail getSessionTravail() {
        return sessionTravail;
    }

    public void setSessionTravail(SessionTravail sessionTravail) {
        this.sessionTravail = sessionTravail;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notationSessionPK != null ? notationSessionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotationSession)) {
            return false;
        }
        NotationSession other = (NotationSession) object;
        if ((this.notationSessionPK == null && other.notationSessionPK != null) || (this.notationSessionPK != null && !this.notationSessionPK.equals(other.notationSessionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NotationSession{" +
                "notationsessionPK=" + notationSessionPK +
                ", valeurnotionsession=" + valeurNotationSession +
                '}';
    }
}
