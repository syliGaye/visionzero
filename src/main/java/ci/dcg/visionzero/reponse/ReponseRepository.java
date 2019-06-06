package ci.dcg.visionzero.reponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReponseRepository extends JpaRepository<Reponse, String> {

    @Query("SELECT r FROM Reponse r WHERE r.libelleReponse = :libelleReponse")
    Reponse findByLibelleReponse(@Param("libelleReponse") String libelleReponse);

    @Query("SELECT r FROM Reponse r WHERE r.valeurReponse = :valeurReponse")
    Reponse findByValeurReponse(@Param("valeurReponse") Integer valeurReponse);

}
