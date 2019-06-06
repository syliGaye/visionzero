package ci.dcg.visionzero.pays;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaysRepository extends JpaRepository<Pays, String> {

    @Query("SELECT p FROM Pays p WHERE p.continent.idContinent = :idContinent")
    List<Pays> findAllByContinent(@Param("idContinent") String s);

    @Query("SELECT p FROM Pays p WHERE p.isoAlphaDeux.idIsoAlphaDeux = :idIsoAlphaDeux")
    Pays findByIsoAlphaDeux(@Param("idIsoAlphaDeux") String s);

    @Query("SELECT p FROM Pays p WHERE p.isoAlphaTrois.idIsoAlphaTrois = :idIsoAlphaTrois")
    Pays findByIsoAlphaTrois(@Param("idIsoAlphaTrois") String s);

}
