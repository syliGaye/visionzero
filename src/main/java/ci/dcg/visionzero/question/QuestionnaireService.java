package ci.dcg.visionzero.question;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface QuestionnaireService extends ServiceFactory<Questionnaire, String> {

	Questionnaire findByLibelleQuestionnaire(String s);

	List<Questionnaire> findAllByEvaluation(String codeEvaluation);

	int countByEvaluation(String codeEvaluation);

}
