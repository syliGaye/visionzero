package ci.dcg.visionzero.notationquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NotationQuestionIndRepository extends JpaRepository<NotationQuestionInd, String> {

    @Query("SELECT nq FROM NotationQuestionInd nq WHERE nq.reponse.codeReponse = :codeReponse")
    List<NotationQuestionInd> findAllByReponse(@Param("codeReponse") String s);

    @Query("SELECT nq FROM NotationQuestionInd nq WHERE nq.questionnaire.codeQuestionnaire = :codeQuestion")
    NotationQuestionInd findByQuestion(@Param("codeQuestion") String s);

    @Query("SELECT nq FROM NotationQuestionInd nq WHERE nq.questionnaire.codeQuestionnaire = :question AND nq.reponse.codeReponse = :reponse")
    NotationQuestionInd findByQuestionAndReponse(@Param("question") String s1, @Param("reponse") String s2);
}
