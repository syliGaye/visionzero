package ci.dcg.visionzero.notationevaluation;

import ci.dcg.visionzero.support.ServiceFactory;

public interface NotationEvaluationIndService  extends ServiceFactory<NotationEvaluationInd, String> {
    NotationEvaluationInd findByEvaluation(String codeEvaluationInd);
}
