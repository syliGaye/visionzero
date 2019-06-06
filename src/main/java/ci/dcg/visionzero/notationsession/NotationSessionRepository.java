package ci.dcg.visionzero.notationsession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotationSessionRepository extends JpaRepository<NotationSession, NotationSessionPK> {
    @Query("SELECT ns FROM NotationSession ns WHERE ns.sessionTravail.codeSession = :codeSession")
    List<NotationSession> findAllBySession(@Param("codeSession") String codeSession);

    @Query("SELECT ns FROM NotationSession ns WHERE ns.axe.codeAxe = :codeAxe")
    List<NotationSession> findAllByAxe(@Param("codeAxe") String codeAxe);

    @Query("SELECT ns FROM NotationSession ns WHERE ns.sessionTravail.codeSession = :codeSession")
    NotationSession findBySession(@Param("codeSession") String codeSession);
}
