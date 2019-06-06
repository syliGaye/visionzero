package ci.dcg.visionzero.notationevaluation;

import ci.dcg.visionzero.support.ServiceFactory;

public interface NotationEvaluationService extends ServiceFactory<NotationEvaluation, String> {
	NotationEvaluation findByEvaluation(String codeEvaluation);
}
