package ci.dcg.visionzero.notationquestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="notationQuestionService")
public class NotationQuestionServiceImpl implements NotationQuestionService {

    @Autowired
    NotationQuestionRepository notationQuestionRepository;

    @Override
    public List<NotationQuestion> findAllByQuestion(String codeQuestion) {
        return notationQuestionRepository.findAllByQuestion(codeQuestion);
    }

    @Override
    public List<NotationQuestion> findAllByReponse(String codeReponse) {
        return notationQuestionRepository.findAllByReponse(codeReponse);
    }

    @Override
    public List<NotationQuestion> findAllByEntreprise(String codeEntreprise) {
        return notationQuestionRepository.findAllByEntreprise(codeEntreprise);
    }

    @Override
    public NotationQuestion findByQuestionnaireAndReponseAndEntreprise(String codeQuestion, String codeReponse, String codeEntreprise) {
        return notationQuestionRepository.findByQuestionnaireAndReponseAndEntreprise(codeQuestion, codeReponse, codeEntreprise);
    }

    @Override
    public NotationQuestion findByQuestionnaireAndEntreprise(String codeQuestion, String codeEntreprise) {
        return notationQuestionRepository.findByQuestionnaireAndEntreprise(codeQuestion, codeEntreprise);
    }

    @Override
    public int countByReponse(String codeReponse) {
        return this.findAllByReponse(codeReponse).size();
    }

    @Override
    public int countByQuestion(String codeQuestion) {
        return this.findAllByQuestion(codeQuestion).size();
    }

    @Override
    public int countByEntreprise(String codeEntreprise) {
        return this.findAllByEntreprise(codeEntreprise).size();
    }

    @Override
    public NotationQuestion getOne(String s) {
        return notationQuestionRepository.getOne(s);
    }

    @Override
    public List<NotationQuestion> findAll() {
        return notationQuestionRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationQuestion save(NotationQuestion notationQuestion) {
        notationQuestion.setCodeNotationQuestionnaire(retourneId());
        return notationQuestionRepository.save(notationQuestion);
    }

    @Override
    public void update(NotationQuestion notationQuestion) {
        notationQuestionRepository.save(notationQuestion);
    }

    @Override
    public void delete(String s) {
        notationQuestionRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationQuestionRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notQuest"+ft.format(date)+""+i;
    }
}
