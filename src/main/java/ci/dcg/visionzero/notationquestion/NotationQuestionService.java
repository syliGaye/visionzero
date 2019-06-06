package ci.dcg.visionzero.notationquestion;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationQuestionService extends ServiceFactory<NotationQuestion, String> {

	NotationQuestion findByQuestion(String codeQuestion);

	List<NotationQuestion> findAllByReponse(String codeReponse);

	NotationQuestion findByQuestionAndReponse(String codeQuestion, String codeReponse);

	int countByReponse(String codeReponse);

}
