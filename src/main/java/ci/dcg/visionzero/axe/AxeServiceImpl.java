package ci.dcg.visionzero.axe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="axeService")
public class AxeServiceImpl implements AxeService {

    @Autowired
    AxeRepository axeRepository;

    @Override
    public Axe getOne(String s) {
        return axeRepository.getOne(s);
    }

    @Override
    public List<Axe> findAll() {
        return axeRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Axe save(Axe axe) {
        return axeRepository.save(axe);
    }

    @Override
    public void update(Axe axe) {
        axeRepository.save(axe);
    }

    @Override
    public void delete(String s) {
        System.out.println("c'est supprimé");
        axeRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return axeRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "axe"+ft.format(date)+""+i;
    }

    @Override
    public Axe findByLibelleAxe(String s) {
        return axeRepository.findByLibelleAxe(s);
    }

    @Override
    public Axe findByQuestionnaire(String codeQuestion) {
        return axeRepository.findByQuestion(codeQuestion);
    }

    @Override
    public List<Axe> findAllByQuestion() {
        return axeRepository.findAllByQuestion();
    }

    @Override
    public int countByQuestion() {
        return this.findAllByQuestion().size();
    }
}