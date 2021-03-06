package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.imageuser.ImageUser;
import org.springframework.web.multipart.MultipartFile;

public class AxeForm {
    private String codeAxe;
    private String libelleAxe;
    private String descriptionAxe;
    private String idCouleur;
    private MultipartFile file;
    private Couleur couleur;
    private ImageUser imageUser;
    private String etat;

    public AxeForm() {
    }

    public AxeForm(String codeAxe, String libelleAxe, String descriptionAxe, String idCouleur) {
        this.codeAxe = codeAxe;
        this.libelleAxe = libelleAxe;
        this.descriptionAxe = descriptionAxe;
        this.idCouleur = idCouleur;
    }

    public AxeForm(String codeAxe, String libelleAxe, String descriptionAxe, String idCouleur, ImageUser imageUser) {
        this.codeAxe = codeAxe;
        this.libelleAxe = libelleAxe;
        this.descriptionAxe = descriptionAxe;
        this.idCouleur = idCouleur;
        this.imageUser = imageUser;
    }

    public AxeForm(String codeAxe, String libelleAxe, String descriptionAxe, String codeCouleur, MultipartFile file) {
        this.codeAxe = codeAxe;
        this.libelleAxe = libelleAxe;
        this.descriptionAxe = descriptionAxe;
        this.idCouleur = codeCouleur;
        this.file = file;
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

    public ImageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageUser imageUser) {
        this.imageUser = imageUser;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Axe createNewAxe(){
        return new Axe(getCodeAxe(), getLibelleAxe(), getDescriptionAxe(), getCouleur(), getImageUser());
    }
}
