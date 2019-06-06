package ci.dcg.visionzero.notationaxe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="notationAxeService")
public class NotationAxeServiceImpl implements NotationAxeService {

    @Autowired
    NotationAxeRepository notationAxeRepository;

    @Override
    public List<NotationAxe> findAllByAxe(String codeAxe) {
        return notationAxeRepository.findAllByAxe(codeAxe);
    }

    @Override
    public int countByAxe(String codeAxe) {
        return this.findAllByAxe(codeAxe).size();
    }

    @Override
    public NotationAxe findByAxe(String codeAxe) {
        return notationAxeRepository.findByAxe(codeAxe);
    }

    @Override
    public NotationAxe getOne(String s) {
        return notationAxeRepository.getOne(s);
    }

    @Override
    public List<NotationAxe> findAll() {
        return notationAxeRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationAxe save(NotationAxe notationAxe) {
        notationAxe.setCodeNotationAxe(this.retourneId());
        return notationAxeRepository.save(notationAxe);
    }

    @Override
    public void update(NotationAxe notationAxe) {
        notationAxeRepository.save(notationAxe);
    }

    @Override
    public void delete(String s) {
        notationAxeRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationAxeRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notAxe"+ft.format(date)+""+i;
    }
}
