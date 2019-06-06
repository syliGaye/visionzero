package ci.dcg.visionzero.couleur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouleurRepository extends JpaRepository<Couleur, String> {
    @Query("SELECT c FROM Couleur c WHERE c.libelleCouleur = :libelleCouleur")
    Couleur findByLibelleCouleur(@Param("libelleCouleur") String s);
}
