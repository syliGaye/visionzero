package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.notationaxe.NotationAxe;
import ci.dcg.visionzero.notationaxe.NotationAxeInd;
import ci.dcg.visionzero.pays.Pays;
import ci.dcg.visionzero.raisonsociale.RaisonSociale;
import ci.dcg.visionzero.secteuractivite.SecteurActivite;
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
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_ENTREPRISE", updatable = false, nullable = false)
    private String codeEntreprise;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 655)
    @Column(name = "NOM_ENTREPRISE", unique = true, nullable = false)
    private String nomEntreprise;

    @Size(max = 1024)
    @Column(name = "DESCRIPTION_ENTREPRISE")
    private String descriptionEntreprise;

    @JsonManagedReference
    @JoinColumn(name = "CODE_PAYS", referencedColumnName = "CODE_PAYS")
    @ManyToOne(optional = false)
    private Pays pays;

    @JsonManagedReference
    @JoinColumn(name = "CODE_SECTEUR_ACTIVITE", referencedColumnName = "CODE_SECTEUR_ACTIVITE")
    @ManyToOne(optional = false)
    private SecteurActivite secteurActivite;

    @JsonManagedReference
    @JoinColumn(name = "CODE_RAISON_SOCIALE", referencedColumnName = "CODE_RAISON_SOCIALE")
    @ManyToOne(optional = false)
    private RaisonSociale raisonSociale;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<NotationAxeInd> notationAxeIndList;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private List<NotationAxe> notationAxeList;

    public Entreprise() {
        super();
    }

    public Entreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    public Entreprise(String nomEntreprise, String descriptionEntreprise, Pays pays, SecteurActivite secteurActivite, RaisonSociale raisonSociale) {
        this.nomEntreprise = nomEntreprise;
        this.descriptionEntreprise = descriptionEntreprise;
        this.pays = pays;
        this.secteurActivite = secteurActivite;
        this.raisonSociale = raisonSociale;
    }

    public String getCodeEntreprise() {
        return codeEntreprise;
    }

    public void setCodeEntreprise(String codeEntreprise) {
        this.codeEntreprise = codeEntreprise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getDescriptionEntreprise() {
        return descriptionEntreprise;
    }

    public void setDescriptionEntreprise(String descriptionEntreprise) {
        this.descriptionEntreprise = descriptionEntreprise;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public SecteurActivite getSecteurActivite() {
        return secteurActivite;
    }

    public void setSecteurActivite(SecteurActivite secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    public RaisonSociale getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(RaisonSociale raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    @XmlTransient
    public List<NotationAxeInd> getNotationAxeIndList() {
        return notationAxeIndList;
    }

    public void setNotationAxeIndList(List<NotationAxeInd> notationAxeIndList) {
        this.notationAxeIndList = notationAxeIndList;
    }

    @XmlTransient
    public List<NotationAxe> getNotationAxeList() {
        return notationAxeList;
    }

    public void setNotationAxeList(List<NotationAxe> notationAxeList) {
        this.notationAxeList = notationAxeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entreprise that = (Entreprise) o;

        return codeEntreprise != null ? codeEntreprise.equals(that.codeEntreprise) : that.codeEntreprise == null;
    }

    @Override
    public int hashCode() {
        return codeEntreprise != null ? codeEntreprise.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entreprise{" +
                "codeEntreprise='" + codeEntreprise + '\'' +
                ", nomEntreprise='" + nomEntreprise + '\'' +
                ", descriptionEntreprise='" + descriptionEntreprise + '\'' +
                ", notationAxeIndList=" + notationAxeIndList +
                ", notationAxeList=" + notationAxeList +
                '}';
    }
}
