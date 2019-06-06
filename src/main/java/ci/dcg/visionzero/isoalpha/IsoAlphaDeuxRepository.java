package ci.dcg.visionzero.isoalpha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IsoAlphaDeuxRepository extends JpaRepository<IsoAlphaDeux, String> {

    @Query("SELECT i2 FROM IsoAlphaDeux i2 WHERE i2.pays.idPays = :codePays")
    IsoAlphaDeux findByPays(@Param("codePays") String s);

    @Query("SELECT i2 FROM IsoAlphaDeux i2 WHERE i2.codeIsoAlphaDeux = :codeIsoAlpha")
    IsoAlphaDeux findByCodeIsoAlphaDeux(@Param("codeIsoAlpha") String s);

}
