package ci.dcg.visionzero.entreprise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {

    @Query("SELECT e FROM Entreprise e WHERE e.nomEntreprise = :nomEntreprise")
    Entreprise findByNomEntreprise(@Param("nomEntreprise") String s);

    @Query("SELECT e FROM Entreprise e WHERE e.pays.idPays = :codePays")
    List<Entreprise> findAllByPays(@Param("codePays") String s);

    @Query("SELECT e FROM Entreprise e WHERE e.raisonSociale.idRaisonSociale = :codeRaisonSociale")
    List<Entreprise> findAllByRaisonSociale(@Param("codeRaisonSociale") String s);

    @Query("SELECT e FROM Entreprise e WHERE e.secteurActivite.idSecteurActivite = :codeSecteurActivite")
    List<Entreprise> findAllBySecteurActivite(@Param("codeSecteurActivite") String s);

    @Query("SELECT e FROM Entreprise e WHERE e.secteurActivite.idSecteurActivite = :codeSecteurActivite AND e.pays.idPays = :codePays")
    List<Entreprise> findAllBySecteurActiviteAndPays(@Param("codeSecteurActivite") String s1, @Param("codePays") String s2);

    @Query("SELECT e FROM Entreprise e WHERE e.secteurActivite.idSecteurActivite = :codeSecteurActivite AND e.raisonSociale.idRaisonSociale = :codeRaisonSociale")
    List<Entreprise> findAllBySecteurActiviteAndRaisonSociale(@Param("codeSecteurActivite") String s1, @Param("codeRaisonSociale") String s2);

    @Query("SELECT e FROM Entreprise e WHERE e.raisonSociale.idRaisonSociale = :codeRaisonSociale AND e.pays.idPays = :codePays")
    List<Entreprise> findAllByRaisonSocialeAndPays(@Param("codeRaisonSociale") String s1, @Param("codePays") String s2);

    @Query("SELECT e FROM Entreprise e WHERE e.secteurActivite.idSecteurActivite = :codeSecteurActivite AND e.raisonSociale.idRaisonSociale = :codeRaisonSociale AND e.pays.idPays = :codePays")
    List<Entreprise> findAllBySecteurActiviteAndRaisonSocialeAndPays(@Param("codeSecteurActivite") String s1, @Param("codeRaisonSociale") String s2, @Param("codePays") String s3);

}
