package ci.dcg.visionzero.notationevaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="notationEvaluationService")
public class NotationEvaluationServiceImpl implements NotationEvaluationService {

    @Autowired
    NotationEvaluationRepository notationEvaluationRepository;

    @Override
    public NotationEvaluation getOne(String s) {
        return notationEvaluationRepository.getOne(s);
    }

    @Override
    public List<NotationEvaluation> findAll() {
        return notationEvaluationRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationEvaluation save(NotationEvaluation notationEvaluation) {
        notationEvaluation.setCodeNotationEvaluation(retourneId());
        return notationEvaluationRepository.save(notationEvaluation);
    }

    @Override
    public void update(NotationEvaluation notationEvaluation) {
        notationEvaluationRepository.save(notationEvaluation);
    }

    @Override
    public void delete(String s) {
        notationEvaluationRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationEvaluationRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notDom"+ft.format(date)+""+i;
    }

    @Override
    public List<NotationEvaluation> findAllByEvaluation(String codeEvaluation) {
        return notationEvaluationRepository.findAllByEvaluation(codeEvaluation);
    }

    @Override
    public List<NotationEvaluation> findAllByEntreprise(String codeEntreprise) {
        return notationEvaluationRepository.findAllByEntreprise(codeEntreprise);
    }

    @Override
    public NotationEvaluation findByEvaluationAndEntreprise(String codeEvaluation, String codeEntreprise) {
        return notationEvaluationRepository.findByEvaluationAndEntreprise(codeEvaluation, codeEntreprise);
    }

    @Override
    public int countByEvaluation(String codeEvaluation) {
        return this.findAllByEvaluation(codeEvaluation).size();
    }

    @Override
    public int countByEntreprise(String codeEntreprise) {
        return this.findAllByEntreprise(codeEntreprise).size();
    }
}
