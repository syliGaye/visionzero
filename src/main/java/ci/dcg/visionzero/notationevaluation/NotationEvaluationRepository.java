package ci.dcg.visionzero.notationevaluation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationEvaluationRepository extends JpaRepository<NotationEvaluation, String> {
    @Query("SELECT ne FROM NotationEvaluation ne WHERE ne.evaluation.codeEvaluation = :codeEvaluation")
    List<NotationEvaluation> findAllByEvaluation(@Param("codeEvaluation") String codeEvaluation);

    @Query("SELECT ne FROM NotationEvaluation ne WHERE ne.entreprise.codeEntreprise = :codeEntreprise")
    List<NotationEvaluation> findAllByEntreprise(@Param("codeEntreprise") String s);

    @Query("SELECT ne FROM NotationEvaluation ne WHERE ne.evaluation.codeEvaluation = :codeEvaluation AND ne.entreprise.codeEntreprise = :codeEntreprise")
    NotationEvaluation findByEvaluationAndEntreprise(@Param("codeEvaluation") String s1, @Param("codeEntreprise") String s2);
}
