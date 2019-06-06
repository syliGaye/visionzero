package ci.dcg.visionzero.sessiontravail;

import ci.dcg.visionzero.notationsession.NotationSession;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
public class SessionTravail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_SESSION", nullable = false, updatable = false)
    private String codeSession;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LIBELLE_SESSION", unique = true, nullable = false)
    private String libelleSession;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sessionTravail")
    private List<NotationSession> notationSessionList;

    public SessionTravail() {
    }

    public SessionTravail(String codeSession) {
        this.codeSession = codeSession;
    }

    public SessionTravail(String codeSession, String libelleSession) {
        this.codeSession = codeSession;
        this.libelleSession = libelleSession;
    }

    public String getCodeSession() {
        return codeSession;
    }

    public void setCodeSession(String codeSession) {
        this.codeSession = codeSession;
    }

    public String getLibelleSession() {
        return libelleSession;
    }

    public void setLibelleSession(String libelleSession) {
        this.libelleSession = libelleSession;
    }

    @XmlTransient
    public List<NotationSession> getNotationSessionList() {
        return notationSessionList;
    }

    public void setNotationSessionList(List<NotationSession> notationSessionList) {
        this.notationSessionList = notationSessionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeSession != null ? codeSession.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SessionTravail)) {
            return false;
        }
        SessionTravail other = (SessionTravail) object;
        if ((this.codeSession == null && other.codeSession != null) || (this.codeSession != null && !this.codeSession.equals(other.codeSession))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SessionTravail{" +
                "codeSession='" + codeSession + '\'' +
                ", libelleSession='" + libelleSession + '\'' +
                ", notationSessionList=" + notationSessionList +
                '}';
    }
}
