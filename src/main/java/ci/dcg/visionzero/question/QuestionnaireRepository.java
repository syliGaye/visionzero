package ci.dcg.visionzero.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, String> {
    @Query("SELECT q FROM Questionnaire q WHERE q.libelleQuestionnaire = :libelle")
    Questionnaire findByLibelleQuestionnaire(@Param("libelle") String s);

    @Query("SELECT q FROM Questionnaire q WHERE q.evaluation.codeEvaluation = :codeEvaluation")
    List<Questionnaire> findAllByEvaluation(@Param("codeEvaluation") String codeEvaluation);

    @Query("SELECT q FROM Questionnaire q WHERE q.evaluation.axe.codeAxe = :codeAxe")
    List<Questionnaire> findAllByAxe(@Param("codeAxe") String s);
}
