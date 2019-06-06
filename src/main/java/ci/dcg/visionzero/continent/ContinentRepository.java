package ci.dcg.visionzero.continent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContinentRepository extends JpaRepository<Continent, String> {
    @Query("SELECT c FROM Continent c WHERE c.libelleContinent = :libelleContinent")
    Continent findByLibelleContinent(@Param("libelleContinent") String s);
}
