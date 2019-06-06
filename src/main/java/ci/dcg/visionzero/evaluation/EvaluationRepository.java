package ci.dcg.visionzero.evaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, String> {
    @Query("SELECT e FROM Evaluation e WHERE e.axe.codeAxe = :codeAxe")
    List<Evaluation> findAllByAxe(@Param("codeAxe") String codeAxe);

    @Query("SELECT e FROM Evaluation e WHERE e.codeEvaluation = (SELECT q.evaluation.codeEvaluation FROM Questionnaire q WHERE q.codeQuestionnaire = :codeQuestion)")
    Evaluation findByQuestion(@Param("codeQuestion") String codeQuestion);

    @Query("SELECT e FROM Evaluation e WHERE e.codeEvaluation in (SELECT q.evaluation.codeEvaluation FROM Questionnaire q)")
    List<Evaluation> findAllByQuestion();
}
