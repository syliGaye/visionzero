package ci.dcg.visionzero.files;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
