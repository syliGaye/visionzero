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
    public NotationQuestion findByQuestion(String codeQuestion) {
        return notationQuestionRepository.findByQuestion(codeQuestion);
    }

    @Override
    public List<NotationQuestion> findAllByReponse(String codeReponse) {
        return notationQuestionRepository.findAllByReponse(codeReponse);
    }

    @Override
    public NotationQuestion findByQuestionAndReponse(String codeQuestion, String codeReponse) {
        return notationQuestionRepository.findByQuestionAndReponse(codeQuestion, codeReponse);
    }

    @Override
    public int countByReponse(String codeReponse) {
        return this.findAllByReponse(codeReponse).size();
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
        notationQuestion.setCodeNotationQuestionnaire(this.retourneId());
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
