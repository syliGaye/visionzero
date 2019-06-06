package ci.dcg.visionzero.notationquestion;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationQuestionIndService extends ServiceFactory<NotationQuestionInd, String> {

    NotationQuestionInd findByQuestion(String codeQuestion);

    List<NotationQuestionInd> findAllByReponse(String codeReponse);

    NotationQuestionInd findByQuestionAndReponse(String codeQuestion, String codeReponse);

    int countByReponse(String codeReponse);

}
