package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.evaluation.Evaluation;
import ci.dcg.visionzero.image.Image;
import ci.dcg.visionzero.notationaxe.NotationAxe;
import ci.dcg.visionzero.notationaxe.NotationAxeInd;
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
public class Axe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_AXE", nullable = false, updatable = false)
    private String codeAxe;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "LIBELLE_AXE", unique = true, nullable = false)
    private String libelleAxe;

    @Size(max = 1024)
    @Column(name = "DESCRIPTION_AXE")
    private String descriptionAxe;

    @JsonManagedReference
    @JoinColumn(name = "CODE_COULEUR", referencedColumnName = "CODE_COULEUR")
    @OneToOne(optional = false)
    private Couleur couleur;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axe")
    private List<Evaluation> evaluationList;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axe")
    private List<NotationAxe> notationAxeList;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "axe")
    private Image image;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "axe")
    private List<NotationAxeInd> notationAxeIndList;

    public Axe() {
    }

    public Axe(String codeAxe) {
        this.codeAxe = codeAxe;
    }

    public Axe(String codeAxe, String libelleAxe) {
        this.codeAxe = codeAxe;
        this.libelleAxe = libelleAxe;
    }

    public String getCodeAxe() {
        return codeAxe;
    }

    public void setCodeAxe(String codeAxe) {
        this.codeAxe = codeAxe;
    }

    public String getLibelleAxe() {
        return libelleAxe;
    }

    public void setLibelleAxe(String libelleAxe) {
        this.libelleAxe = libelleAxe;
    }

    public String getDescriptionAxe() {
        return descriptionAxe;
    }

    public void setDescriptionAxe(String descriptionAxe) {
        this.descriptionAxe = descriptionAxe;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    @XmlTransient
    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @XmlTransient
    public List<NotationAxe> getNotationAxeList() {
        return notationAxeList;
    }

    public void setNotationAxeList(List<NotationAxe> notationAxeList) {
        this.notationAxeList = notationAxeList;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @XmlTransient
    public List<NotationAxeInd> getNotationAxeIndList() {
        return notationAxeIndList;
    }

    public void setNotationAxeIndList(List<NotationAxeInd> notationAxeIndList) {
        this.notationAxeIndList = notationAxeIndList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeAxe != null ? codeAxe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Axe)) {
            return false;
        }
        Axe other = (Axe) object;
        if ((this.codeAxe == null && other.codeAxe != null) || (this.codeAxe != null && !this.codeAxe.equals(other.codeAxe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Axe{" +
                "codeAxe='" + codeAxe + '\'' +
                ", libelleAxe='" + libelleAxe + '\'' +
                ", descriptionAxe='" + descriptionAxe + '\'' +
                ", evaluationList=" + evaluationList +
                ", notationAxeList=" + notationAxeList +
                ", image=" + image +
                '}';
    }
}
