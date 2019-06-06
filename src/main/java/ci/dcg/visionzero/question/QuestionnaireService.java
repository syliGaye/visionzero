package ci.dcg.visionzero.question;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface QuestionnaireService extends ServiceFactory<Questionnaire, String> {

	List<Questionnaire> findAllByEvaluation(String codeEvaluation);

	int countByEvaluation(String codeEvaluation);

}
