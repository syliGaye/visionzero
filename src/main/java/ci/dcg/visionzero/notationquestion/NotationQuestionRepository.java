package ci.dcg.visionzero.notationquestion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationQuestionRepository  extends JpaRepository<NotationQuestion, String> {
    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.reponse.codeReponse = :codeReponse")
    List<NotationQuestion> findAllByReponse(@Param("codeReponse") String s);

    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.questionnaire.codeQuestionnaire = :codeQuestion")
    List<NotationQuestion> findAllByQuestion(@Param("codeQuestion") String s);

    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.entreprise.codeEntreprise = :codeEntreprise")
    List<NotationQuestion> findAllByEntreprise(@Param("codeEntreprise") String s);

    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.questionnaire.codeQuestionnaire = :codeQuestion AND nq.reponse.codeReponse = :codeReponse AND nq.entreprise.codeEntreprise = :codeEntreprise")
    NotationQuestion findByQuestionnaireAndReponseAndEntreprise(@Param("codeQuestion") String s1,
                                                                @Param("codeReponse") String s2,
                                                                @Param("codeEntreprise") String s3);
    @Query("SELECT nq FROM NotationQuestion nq WHERE nq.questionnaire.codeQuestionnaire = :codeQuestion AND nq.entreprise.codeEntreprise = :codeEntreprise")
    NotationQuestion findByQuestionnaireAndEntreprise(@Param("codeQuestion") String s1, @Param("codeEntreprise") String s2);
}
