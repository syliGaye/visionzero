package ci.dcg.visionzero.notationevaluation;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationEvaluationService extends ServiceFactory<NotationEvaluation, String> {
	List<NotationEvaluation> findAllByEvaluation(String codeEvaluation);

	List<NotationEvaluation> findAllByEntreprise(String codeEntreprise);

	NotationEvaluation findByEvaluationAndEntreprise(String codeEvaluation, String codeEntreprise);

	int countByEvaluation(String codeEvaluation);

	int countByEntreprise(String codeEntreprise);
}
