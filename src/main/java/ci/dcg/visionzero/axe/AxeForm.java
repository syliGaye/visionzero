package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.image.Image;
import org.springframework.web.multipart.MultipartFile;

public class AxeForm {
    private String codeAxe;
    private String libelleAxe;
    private String descriptionAxe;
    private String idCouleur;
    private MultipartFile file;
    private Couleur couleur;
    private Image image;
    private String etat;

    public AxeForm() {
    }

    public AxeForm(String codeAxe, String libelleAxe, String descriptionAxe, String idCouleur) {
        this.codeAxe = codeAxe;
        this.libelleAxe = libelleAxe;
        this.descriptionAxe = descriptionAxe;
        this.idCouleur = idCouleur;
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

    public String getIdCouleur() {
        return idCouleur;
    }

    public void setIdCouleur(String idCouleur) {
        this.idCouleur = idCouleur;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Axe createNewAxe(){
        return new Axe(getCodeAxe(), getLibelleAxe(), getDescriptionAxe(), getCouleur(), getImage());
    }
}
