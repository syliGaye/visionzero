package ci.dcg.visionzero.files;

import ci.dcg.visionzero.image.Image;
import ci.dcg.visionzero.imageuser.ImageUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    /*
    public void storeFile(Image image) {
        // Normalize file name
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = image.getFileName() + "." + image.getFileType();

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            //Files.copy(image.getFile(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Files.write(targetLocation, image.getFile());

            //return image;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
*/

    public void storeFileUser(ImageUser imageUser) {
        // Normalize file name
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileNameUser = imageUser.getFileNameUser() + "." + imageUser.getFileTypeUser();

        try {
            // Check if the file's name contains invalid characters
            if(fileNameUser.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileNameUser);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileNameUser);
            //Files.copy(image.getFile(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            Files.write(targetLocation, imageUser.getFileUser());

            //return image;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileNameUser + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
