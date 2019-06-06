package ci.dcg.visionzero.isoalpha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IsoAlphaTroisRepository extends JpaRepository<IsoAlphaTrois, String> {

    @Query("SELECT i3 FROM IsoAlphaTrois i3 WHERE i3.pays.idPays = :codePays")
    IsoAlphaTrois findByPays(@Param("codePays") String s);

    @Query("SELECT i3 FROM IsoAlphaTrois i3 WHERE i3.codeIsoAlphaTrois = :codeIsoAlpha")
    IsoAlphaTrois findByCodeIsoAlphaTrois(@Param("codeIsoAlpha") String s);

}
