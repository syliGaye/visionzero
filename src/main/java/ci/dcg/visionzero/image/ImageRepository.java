package ci.dcg.visionzero.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("SELECT i FROM Image i WHERE i.axe.codeAxe = :codeAxe")
    Image findByAxe(@Param("codeAxe") String codeAxe);
}
