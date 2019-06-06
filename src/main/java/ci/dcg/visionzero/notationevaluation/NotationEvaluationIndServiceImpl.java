package ci.dcg.visionzero.notationevaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "notationEvaluationIndService")
public class NotationEvaluationIndServiceImpl implements NotationEvaluationIndService {

    @Autowired
    private NotationEvaluationIndRepository notationEvaluationIndRepository;

    @Override
    public NotationEvaluationInd findByEvaluation(String codeEvaluationInd) {
        return notationEvaluationIndRepository.findByEvaluation(codeEvaluationInd);
    }

    @Override
    public NotationEvaluationInd getOne(String s) {
        return notationEvaluationIndRepository.getOne(s);
    }

    @Override
    public List<NotationEvaluationInd> findAll() {
        return notationEvaluationIndRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationEvaluationInd save(NotationEvaluationInd notationEvaluationInd) {
        notationEvaluationInd.setCodeNotationEvaluationInd(this.retourneId());
        return notationEvaluationIndRepository.save(notationEvaluationInd);
    }

    @Override
    public void update(NotationEvaluationInd notationEvaluationInd) {
        notationEvaluationIndRepository.save(notationEvaluationInd);
    }

    @Override
    public void delete(String s) {
        notationEvaluationIndRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationEvaluationIndRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notDomInd"+ft.format(date)+""+i;
    }
}
