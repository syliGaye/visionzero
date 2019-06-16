package ci.dcg.visionzero.imageuser;

import ci.dcg.visionzero.utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Arrays;

@Entity
public class ImageUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_IMAGE_USER", nullable = false, updatable = false)
    private String codeImageUser;

    @Lob
    @Column(name = "IMAGE_USER", nullable = false)
    private byte[] fileUser;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NOM_IMAGE_USER", unique = true, nullable = false)
    private String fileNameUser;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LIEN_IMAGE_USER", nullable = false)
    private String fileDownloadUriUser;

    @NotNull
    @Column(name = "TYPE_IMAGE_USER", nullable = false)
    private String fileTypeUser;

    @NotNull
    @Column(name = "TAILLE_IMAGE_USER", nullable = false)
    private long fileSizeUser;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "imageUser")
    private Utilisateur utilisateur;

    public ImageUser() {
    }

    public ImageUser(byte[] fileUser, String fileNameUser, String fileDownloadUriUser, String fileTypeUser, long fileSizeUser) {
        this.fileUser = fileUser;
        this.fileNameUser = fileNameUser;
        this.fileDownloadUriUser = fileDownloadUriUser;
        this.fileTypeUser = fileTypeUser;
        this.fileSizeUser = fileSizeUser;
    }

    public ImageUser(String codeImageUser, byte[] fileUser, String fileDownloadUriUser, String fileNameUser, String fileTypeUser, long fileSizeUser) {
        this.codeImageUser = codeImageUser;
        this.fileUser = fileUser;
        this.fileNameUser = fileNameUser;
        this.fileDownloadUriUser = fileDownloadUriUser;
        this.fileTypeUser = fileTypeUser;
        this.fileSizeUser = fileSizeUser;
    }

    public String getCodeImageUser() {
        return codeImageUser;
    }

    public void setCodeImageUser(String codeImageUser) {
        this.codeImageUser = codeImageUser;
    }

    public byte[] getFileUser() {
        return fileUser;
    }

    public void setFileUser(byte[] fileUser) {
        this.fileUser = fileUser;
    }

    public String getFileNameUser() {
        return fileNameUser;
    }

    public void setFileNameUser(String fileNameUser) {
        this.fileNameUser = fileNameUser;
    }

    public String getFileDownloadUriUser() {
        return fileDownloadUriUser;
    }

    public void setFileDownloadUriUser(String fileDownloadUriUser) {
        this.fileDownloadUriUser = fileDownloadUriUser;
    }

    public String getFileTypeUser() {
        return fileTypeUser;
    }

    public void setFileTypeUser(String fileTypeUser) {
        this.fileTypeUser = fileTypeUser;
    }

    public long getFileSizeUser() {
        return fileSizeUser;
    }

    public void setFileSizeUser(long fileSizeUser) {
        this.fileSizeUser = fileSizeUser;
    }

    @XmlTransient
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImageUser imageUser = (ImageUser) o;

        if (fileSizeUser != imageUser.fileSizeUser) return false;
        if (codeImageUser != null ? !codeImageUser.equals(imageUser.codeImageUser) : imageUser.codeImageUser != null)
            return false;
        if (!Arrays.equals(fileUser, imageUser.fileUser)) return false;
        if (fileNameUser != null ? !fileNameUser.equals(imageUser.fileNameUser) : imageUser.fileNameUser != null)
            return false;
        if (fileDownloadUriUser != null ? !fileDownloadUriUser.equals(imageUser.fileDownloadUriUser) : imageUser.fileDownloadUriUser != null)
            return false;
        return fileTypeUser != null ? fileTypeUser.equals(imageUser.fileTypeUser) : imageUser.fileTypeUser == null;
    }

    @Override
    public int hashCode() {
        int result = codeImageUser != null ? codeImageUser.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(fileUser);
        result = 31 * result + (fileNameUser != null ? fileNameUser.hashCode() : 0);
        result = 31 * result + (fileDownloadUriUser != null ? fileDownloadUriUser.hashCode() : 0);
        result = 31 * result + (fileTypeUser != null ? fileTypeUser.hashCode() : 0);
        result = 31 * result + (int) (fileSizeUser ^ (fileSizeUser >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ImageUser{" +
                "codeImageUser='" + codeImageUser + '\'' +
                ", fileUser=" + Arrays.toString(fileUser) +
                ", fileNameUser='" + fileNameUser + '\'' +
                ", fileDownloadUriUser='" + fileDownloadUriUser + '\'' +
                ", fileTypeUser='" + fileTypeUser + '\'' +
                ", fileSizeUser=" + fileSizeUser +
                '}';
    }
}
