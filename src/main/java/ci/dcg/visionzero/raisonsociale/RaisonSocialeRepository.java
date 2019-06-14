package ci.dcg.visionzero.raisonsociale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RaisonSocialeRepository extends JpaRepository<RaisonSociale, String> {

    @Query("SELECT rs FROM RaisonSociale rs WHERE rs.libelleRaisonSociale = :libelle")
    RaisonSociale findByLibelleRaisonSociale(@Param("libelle") String s);

}
