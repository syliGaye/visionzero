package ci.dcg.visionzero.secteuractivite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SecteurActiviteRepository extends JpaRepository<SecteurActivite, String> {
    @Query("SELECT sa FROM SecteurActivite sa WHERE sa.libelleSecteurActivite = :secteuractivite")
    SecteurActivite findByLibelleSecteurActivite(@Param("secteuractivite") String s);
}
