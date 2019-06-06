package ci.dcg.visionzero.sessiontravail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionTravailRepository extends JpaRepository<SessionTravail, String> {
    /*@Query("SELECT st FROM SessionTravail st GROUP BY st.codeSession desc LIMIT 4")
    List<SessionTravail> findAllDescFour();*/

    @Query("SELECT st FROM SessionTravail st WHERE st.libelleSession = :libelleSession")
    SessionTravail findByLibelleSession(@Param("libelleSession") String libelleSession);
}
