package ci.dcg.visionzero.notationaxe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationAxeRepository extends JpaRepository<NotationAxe, String> {
    @Query("SELECT na FROM NotationAxe na WHERE na.axe.codeAxe = :codeAxe")
    List<NotationAxe> findAllByAxe(@Param("codeAxe") String s);

    @Query("SELECT na FROM NotationAxe na WHERE na.axe.codeAxe = :codeAxe")
    NotationAxe findByAxe(@Param("codeAxe") String s);

    @Query("SELECT na FROM NotationAxe na WHERE na.entreprise.codeEntreprise = :codeEntreprise")
    List<NotationAxe> findAllByEntreprise(@Param("codeEntreprise") String s);

    @Query("SELECT na FROM NotationAxe na WHERE na.entreprise.codeEntreprise = :codeEntreprise")
    NotationAxe findByEntreprise(@Param("codeEntreprise") String s);
}
