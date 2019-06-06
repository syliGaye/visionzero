package ci.dcg.visionzero.notationaxe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "notationAxeIndService")
public class NotationAxeIndServiceImpl implements NotationAxeIndService {

    @Autowired
    private NotationAxeIndRepository notationAxeIndRepository;

    @Override
    public List<NotationAxeInd> findAllByAxe(String codeAxe) {
        return notationAxeIndRepository.findAllByAxe(codeAxe);
    }

    @Override
    public List<NotationAxeInd> findAllByEntreprise(String codeEntreprise) {
        return notationAxeIndRepository.findAllByEntreprise(codeEntreprise);
    }

    @Override
    public int countByAxe(String codeAxe) {
        return this.findAllByAxe(codeAxe).size();
    }

    @Override
    public int countByEntreprise(String codeEntreprise) {
        return this.findAllByEntreprise(codeEntreprise).size();
    }

    @Override
    public NotationAxeInd findByAxeAndEntreprise(String codeAxe, String codeEntreprise) {
        return notationAxeIndRepository.findAllByAxeAndEntreprise(codeAxe, codeEntreprise);
    }

    @Override
    public NotationAxeInd getOne(String s) {
        return notationAxeIndRepository.getOne(s);
    }

    @Override
    public List<NotationAxeInd> findAll() {
        return notationAxeIndRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationAxeInd save(NotationAxeInd notationAxeInd) {
        notationAxeInd.setCodeNotationAxeInd(this.retourneId());
        return notationAxeIndRepository.save(notationAxeInd);
    }

    @Override
    public void update(NotationAxeInd notationAxeInd) {
        notationAxeIndRepository.save(notationAxeInd);
    }

    @Override
    public void delete(String s) {
        notationAxeIndRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationAxeIndRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notAxeInd"+ft.format(date)+""+i;
    }
}
