package ci.dcg.visionzero.notationquestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "notationQuestionIndService")
public class NotationQuestionIndServiceImpl implements NotationQuestionIndService {

    @Autowired
    private NotationQuestionIndRepository notationQuestionIndRepository;

    @Override
    public NotationQuestionInd findByQuestion(String codeQuestion) {
        return notationQuestionIndRepository.findByQuestion(codeQuestion);
    }

    @Override
    public List<NotationQuestionInd> findAllByReponse(String codeReponse) {
        return notationQuestionIndRepository.findAllByReponse(codeReponse);
    }

    @Override
    public NotationQuestionInd findByQuestionAndReponse(String codeQuestion, String codeReponse) {
        return notationQuestionIndRepository.findByQuestionAndReponse(codeQuestion, codeReponse);
    }

    @Override
    public int countByReponse(String codeReponse) {
        return this.findAllByReponse(codeReponse).size();
    }

    @Override
    public NotationQuestionInd getOne(String s) {
        return notationQuestionIndRepository.getOne(s);
    }

    @Override
    public List<NotationQuestionInd> findAll() {
        return notationQuestionIndRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationQuestionInd save(NotationQuestionInd notationQuestionInd) {
        notationQuestionInd.setCodNotationQuestionInd(this.retourneId());
        return notationQuestionIndRepository.save(notationQuestionInd);
    }

    @Override
    public void update(NotationQuestionInd notationQuestionInd) {
        notationQuestionIndRepository.save(notationQuestionInd);
    }

    @Override
    public void delete(String s) {
        notationQuestionIndRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return notationQuestionIndRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "notQuestInd"+ft.format(date)+""+i;
    }
}
