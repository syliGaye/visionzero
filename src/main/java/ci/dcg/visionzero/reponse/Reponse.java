package ci.dcg.visionzero.reponse;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.notationquestion.NotationQuestion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Reponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_REPONSE", nullable = false, updatable = false)
    private String codeReponse;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LIBELLE_REPONSE", unique = true, nullable = false)
    private String libelleReponse;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALEUR_REPONSE", unique = true, nullable = false)
    private Integer valeurReponse;

    @JsonManagedReference
    @JoinColumn(name = "CODE_COULEUR", referencedColumnName = "CODE_COULEUR")
    @OneToOne(optional = false)
    private Couleur couleur;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reponse")
    private List<NotationQuestion> notationQuestionList;

    public Reponse() {
    }

    public Reponse(String codeReponse) {
        this.codeReponse = codeReponse;
    }

    public Reponse(String codeReponse, String libelleReponse, int valeurReponse) {
        this.codeReponse = codeReponse;
        this.libelleReponse = libelleReponse;
        this.valeurReponse = valeurReponse;
    }

    public Reponse(String libelleReponse, Integer valeurReponse, Couleur couleur) {
        this.couleur = couleur;
        this.libelleReponse = libelleReponse;
        this.valeurReponse = valeurReponse;
    }

    public String getCodeReponse() {
        return codeReponse;
    }

    public void setCodeReponse(String codeReponse) {
        this.codeReponse = codeReponse;
    }

    public String getLibelleReponse() {
        return libelleReponse;
    }

    public void setLibelleReponse(String libelleReponse) {
        this.libelleReponse = libelleReponse;
    }

    public Integer getValeurReponse() {
        return valeurReponse;
    }

    public void setValeurReponse(Integer valeurReponse) {
        this.valeurReponse = valeurReponse;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @XmlTransient
    public List<NotationQuestion> getNotationQuestionList() {
        return notationQuestionList;
    }

    public void setNotationQuestionList(List<NotationQuestion> notationQuestionList) {
        this.notationQuestionList = notationQuestionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeReponse != null ? codeReponse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reponse)) {
            return false;
        }
        Reponse other = (Reponse) object;
        if ((this.codeReponse == null && other.codeReponse != null) || (this.codeReponse != null && !this.codeReponse.equals(other.codeReponse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "codeReponse='" + codeReponse + '\'' +
                ", libelleReponse='" + libelleReponse + '\'' +
                ", valeurReponse=" + valeurReponse +
                ", notationQuestionList=" + notationQuestionList +
                '}';
    }
}
