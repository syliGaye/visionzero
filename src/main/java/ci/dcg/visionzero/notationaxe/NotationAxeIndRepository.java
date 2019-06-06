package ci.dcg.visionzero.notationaxe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationAxeIndRepository extends JpaRepository<NotationAxeInd, String> {
    @Query("SELECT nai FROM NotationAxeInd nai WHERE nai.axe.codeAxe = :codeAxe")
    List<NotationAxeInd> findAllByAxe(@Param("codeAxe") String s);

    /*@Query("SELECT nai FROM NotationAxeInd nai WHERE nai.axe.codeAxe = :codeAxe")
    NotationAxeInd findByAxe(@Param("codeAxe") String s);*/

    @Query("SELECT nai FROM NotationAxeInd nai WHERE nai.entreprise.codeEntreprise = :codeEntreprise")
    List<NotationAxeInd> findAllByEntreprise(@Param("codeEntreprise") String s);

    @Query("SELECT nai FROM NotationAxeInd nai WHERE nai.axe.codeAxe = :codeAxe AND nai.entreprise.codeEntreprise = :codeEntreprise")
    NotationAxeInd findAllByAxeAndEntreprise(@Param("codeAxe") String s1, @Param("codeEntreprise") String s2);

}
