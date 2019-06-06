package ci.dcg.visionzero.axe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AxeRepository extends JpaRepository<Axe, String> {
    @Query("SELECT a FROM Axe a WHERE a.codeAxe = (SELECT e.axe.codeAxe FROM Evaluation e WHERE e.codeEvaluation = (SELECT q.evaluation.codeEvaluation FROM Questionnaire q WHERE q.codeQuestionnaire = :codeQuestion))")
    Axe findByQuestion(@Param("codeQuestion") String codeQuestion);

    @Query("SELECT a FROM Axe a WHERE a.codeAxe in (SELECT e.axe.codeAxe FROM Evaluation e WHERE e.codeEvaluation in (SELECT q.evaluation.codeEvaluation FROM Questionnaire q))")
    List<Axe> findAllByQuestion();

}
