package ci.dcg.visionzero.notationquestion;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationQuestionService extends ServiceFactory<NotationQuestion, String> {

	List<NotationQuestion> findAllByQuestion(String codeQuestion);

	List<NotationQuestion> findAllByReponse(String codeReponse);

	List<NotationQuestion> findAllByEntreprise(String codeEntreprise);

	NotationQuestion findByQuestionnaireAndReponseAndEntreprise(String codeQuestion, String codeReponse, String codeEntreprise);

	NotationQuestion findByQuestionnaireAndEntreprise(String codeQuestion, String codeEntreprise);

	int countByReponse(String codeReponse);

	int countByQuestion(String codeQuestion);

	int countByEntreprise(String codeEntreprise);

}
