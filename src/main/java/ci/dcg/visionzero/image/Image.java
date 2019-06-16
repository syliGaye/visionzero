package ci.dcg.visionzero.image;

import ci.dcg.visionzero.axe.Axe;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "CODE_IMAGE", updatable = false, nullable = false)
    private String codeImage;

    @Lob
    @Column(name = "IMAGE", nullable = false)
    private byte[] file;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NOM_IMAGE", unique = true, nullable = false)
    private String fileName;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "LIEN_IMAGE", nullable = false)
    private String fileDownloadUri;

    @NotNull
    @Column(name = "TYPE_IMAGE", nullable = false)
    private String fileType;

    @NotNull
    @Column(name = "TAILLE_IMAGE", nullable = false)
    private long size;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "image")
    private Axe axe;

    public Image() {
    }

    public Image(String codeImage) {
        this.codeImage = codeImage;
    }

    public Image(byte[] file) {
        this.file = file;
    }

    public Image(String codeImage, byte[] file) {
        this.codeImage = codeImage;
        this.file = file;
    }

    public Image(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public Image(String codeImage, byte[] file, String fileName, String fileDownloadUri, String fileType, long size) {
        this.codeImage = codeImage;
        this.file = file;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public Image(byte[] file, String fileName, String fileDownloadUri, String fileType, long size) {
        this.file = file;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public Image(String codeImage, byte[] file, String fileName, String fileDownloadUri, String fileType, long size, Axe axe) {
        this.codeImage = codeImage;
        this.file = file;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.axe = axe;
    }

    public String getCodeImage() {
        return codeImage;
    }

    public void setCodeImage(String codeImage) {
        this.codeImage = codeImage;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    @Override
    public int hashCode() {
        return codeImage != null ? codeImage.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Image{" +
                "codeImage='" + codeImage + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileType='" + fileType + '\'' +
                ", size=" + size +
                '}';
    }
}
