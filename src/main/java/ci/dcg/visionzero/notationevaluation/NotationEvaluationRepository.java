package ci.dcg.visionzero.notationevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotationEvaluationRepository extends JpaRepository<NotationEvaluation, String> {
    @Query("SELECT ne FROM NotationEvaluation ne WHERE ne.evaluation.codeEvaluation = :codeEvaluation")
    NotationEvaluation findByEvaluation(@Param("codeEvaluation") String codeEvaluation);
}
