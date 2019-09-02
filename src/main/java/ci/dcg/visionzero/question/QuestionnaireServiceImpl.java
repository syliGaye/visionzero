package ci.dcg.visionzero.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Override
    public Questionnaire findByLibelleQuestionnaire(String s) {
        return questionnaireRepository.findByLibelleQuestionnaire(s);
    }

    @Override
    public List<Questionnaire> findAllByEvaluation(String codeEvaluation) {
        return questionnaireRepository.findAllByEvaluation(codeEvaluation);
    }

    @Override
    public List<Questionnaire> findAllByAxe(String s) {
        return questionnaireRepository.findAllByAxe(s);
    }

    @Override
    public int countByEvaluation(String codeEvaluation) {
        return this.findAllByEvaluation(codeEvaluation).size();
    }

    @Override
    public int countByAxe(String s) {
        return this.findAllByAxe(s).size();
    }

    @Override
    public Questionnaire getOne(String s) {
        return questionnaireRepository.getOne(s);
    }

    @Override
    public List<Questionnaire> findAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Questionnaire save(Questionnaire questionnaire) {
        questionnaire.setCodeQuestionnaire(this.retourneId());
        return questionnaireRepository.save(questionnaire);
    }

    @Override
    public void update(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

    @Override
    public void delete(String s) {
        questionnaireRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return questionnaireRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "quest"+ft.format(date)+""+i;
    }
}
