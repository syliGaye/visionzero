package ci.dcg.visionzero.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image findByAxe(String codeAxe) {
        return imageRepository.findByAxe(codeAxe);
    }

    @Override
    public Image getOne(String s) {
        return imageRepository.getOne(s);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Image save(Image image) {
        image.setCodeImage(retourneId());
        return imageRepository.save(image);
    }

    @Override
    public void update(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void delete(String s) {
        imageRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return imageRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "img"+ft.format(date)+""+i;
    }

    /*@Override
    public Image getImageOne(String codeImage) {
        return imageRepository.getOne(codeImage);
    }
    
    @Override
    public Image findImageByUser(String idUser) {
        return imageRepository.findByUser(idUser);
    }

    @Override
    public Image findImageByAxe(String codeAxe) {
        return imageRepository.findByAxe(codeAxe);
    }

    @Override
    public List<Image> findAllImage() {
        return imageRepository.findAll();
    }

    @Override
    public int countImage() {
        return findAllImage().size();
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void updateImage(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void deleteImage(String codeImage) {
        imageRepository.delete(codeImage);
    }

    @Override
    public boolean isImageExist(String codeImage) {
        return imageRepository.exists(codeImage);
    }

    @Override
    public String returnImageId() {

    }

	@Override
	public Image findImageById(String codeImage) {
		return imageRepository.findOne(codeImage);
	}*/
}
