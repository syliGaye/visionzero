package ci.dcg.visionzero.imageuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="imageUserService")
public class ImageUserServiceImpl implements ImageUserService {

    @Autowired
    private ImageUserRepository imageUserRepository;

    @Override
    public ImageUser getOne(String s) {
        return imageUserRepository.getOne(s);
    }

    @Override
    public List<ImageUser> findAll() {
        return imageUserRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public ImageUser save(ImageUser imageUser) {
        //imageUser.setCodeImageUser(this.retourneId());
        return imageUserRepository.save(imageUser);
    }

    @Override
    public void update(ImageUser imageUser) {
        imageUserRepository.save(imageUser);
    }

    @Override
    public void delete(String s) {
        imageUserRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return imageUserRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "imgUser"+ft.format(date)+""+i;
    }
}
