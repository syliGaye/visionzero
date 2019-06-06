package ci.dcg.visionzero.notationevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotationEvaluationIndRepository extends JpaRepository<NotationEvaluationInd, String> {
    @Query("SELECT nei FROM NotationEvaluationInd nei WHERE nei.evaluation.codeEvaluation = :codeEvaluation")
    NotationEvaluationInd findByEvaluation(@Param("codeEvaluation") String codeEvaluation);
}
