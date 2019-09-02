package ci.dcg.visionzero.couleur;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.reponse.Reponse;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
public class Couleur implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_COULEUR", updatable = false, nullable = false)
    private String codeCouleur;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LIBELLE_COULEUR", unique = true, nullable = false)
    private String libelleCouleur;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "RGB_COULEUR", unique = true, nullable = false)
    private String rgbCouleur;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "HEX_COULEUR", unique = true, nullable = false)
    private String hexCouleur;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "couleur")
    private Reponse reponse;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "couleur")
    private Axe axe;

    public Couleur() {
    }

    public Couleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public Couleur(String libelleCouleur, String rgbCouleur, String hexCouleur) {
        this.libelleCouleur = libelleCouleur;
        this.rgbCouleur = rgbCouleur;
        this.hexCouleur = hexCouleur;
    }

    public Couleur(String codeCouleur, String libelleCouleur, String rgbCouleur, String hexCouleur) {
        this.codeCouleur = codeCouleur;
        this.libelleCouleur = libelleCouleur;
        this.rgbCouleur = rgbCouleur;
        this.hexCouleur = hexCouleur;
    }

    public String getCodeCouleur() {
        return codeCouleur;
    }

    public void setCodeCouleur(String codeCouleur) {
        this.codeCouleur = codeCouleur;
    }

    public String getLibelleCouleur() {
        return libelleCouleur;
    }

    public void setLibelleCouleur(String libelleCouleur) {
        this.libelleCouleur = libelleCouleur;
    }

    public String getRgbCouleur() {
        return rgbCouleur;
    }

    public void setRgbCouleur(String rgbCouleur) {
        this.rgbCouleur = rgbCouleur;
    }

    public String getHexCouleur() {
        return hexCouleur;
    }

    public void setHexCouleur(String hexCouleur) {
        this.hexCouleur = hexCouleur;
    }

    @XmlTransient
    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @XmlTransient
    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Couleur couleur = (Couleur) o;

        return codeCouleur != null ? codeCouleur.equals(couleur.codeCouleur) : couleur.codeCouleur == null;
    }

    @Override
    public int hashCode() {
        return codeCouleur != null ? codeCouleur.hashCode() : 0;
    }

}
