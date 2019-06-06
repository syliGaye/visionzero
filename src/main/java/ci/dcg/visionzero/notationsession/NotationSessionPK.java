package ci.dcg.visionzero.notationsession;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class NotationSessionPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
    @Column(name = "CODE_SESSION", nullable = false)
    private String codeSession;

    @NotNull
    @Column(name = "CODE_AXE", nullable = false)
    private String codeAxe;

    public NotationSessionPK() {
    }

    public NotationSessionPK(String codeSession, String codeAxe) {
        this.codeSession = codeSession;
        this.codeAxe = codeAxe;
    }

    public String getCodeSession() {
        return codeSession;
    }

    public void setCodeSession(String codeSession) {
        this.codeSession = codeSession;
    }

    public String getCodeAxe() {
        return codeAxe;
    }

    public void setCodeAxe(String codeAxe) {
        this.codeAxe = codeAxe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotationSessionPK that = (NotationSessionPK) o;

        if (!codeSession.equals(that.codeSession)) return false;
        return codeAxe.equals(that.codeAxe);
    }

    @Override
    public int hashCode() {
        int result = codeSession.hashCode();
        result = 31 * result + codeAxe.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NotationSessionPK{" +
                "codeSession='" + codeSession + '\'' +
                ", codeAxe='" + codeAxe + '\'' +
                '}';
    }
}
