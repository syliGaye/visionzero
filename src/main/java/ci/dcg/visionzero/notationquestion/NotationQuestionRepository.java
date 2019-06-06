package ci.dcg.visionzero.notationquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationQuestionRepository  extends JpaRepository<NotationQuestion, String> {
    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.reponse.codeReponse = :codeReponse")
    List<NotationQuestion> findAllByReponse(@Param("codeReponse") String codeReponse);

    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.questionnaire.codeQuestionnaire = :codeQuestion")
    NotationQuestion findByQuestion(@Param("codeQuestion") String codeQuestion);

    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.questionnaire.codeQuestionnaire = :question AND nq.reponse.codeReponse = :reponse")
    NotationQuestion findByQuestionAndReponse(@Param("question") String s1, @Param("reponse") String s2);
}
