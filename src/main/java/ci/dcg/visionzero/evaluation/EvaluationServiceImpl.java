package ci.dcg.visionzero.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "evaluationService")
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;

    @Override
    public Evaluation findByQuestionnaire(String codeQuestion) {
        return evaluationRepository.findByQuestion(codeQuestion);
    }

    @Override
    public List<Evaluation> findAllByAxe(String codeAxe) {
        return evaluationRepository.findAllByAxe(codeAxe);
    }

    @Override
    public List<Evaluation> findAllByQuestion() {
        return evaluationRepository.findAllByQuestion();
    }

    @Override
    public int countByQuestion() {
        return this.findAllByQuestion().size();
    }

    @Override
    public int countByAxe(String codeAxe) {
        return this.findAllByAxe(codeAxe).size();
    }

    @Override
    public Evaluation getOne(String s) {
        return evaluationRepository.getOne(s);
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Evaluation save(Evaluation evaluation) {
        evaluation.setCodeEvaluation(this.retourneId());
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void update(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
    }

    @Override
    public void delete(String s) {
        evaluationRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return evaluationRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "dom"+ft.format(date)+""+i;
    }
}