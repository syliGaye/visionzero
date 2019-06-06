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
    public NotationEvaluation findByEvaluation(String codeEvaluation) {
        return notationEvaluationRepository.findByEvaluation(codeEvaluation);
    }

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
        notationEvaluation.setCodeNotationEvaluation(this.retourneId());
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
}
