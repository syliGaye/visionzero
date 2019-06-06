package ci.dcg.visionzero.evaluation;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface EvaluationService extends ServiceFactory<Evaluation, String> {

	Evaluation findByQuestionnaire(String codeQuestion);

	List<Evaluation> findAllByAxe(String codeAxe);

	List<Evaluation> findAllByQuestion();

	int countByQuestion();

	int countByAxe(String codeAxe);
}
