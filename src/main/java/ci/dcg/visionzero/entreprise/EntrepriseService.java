package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface EntrepriseService extends ServiceFactory<Entreprise, String> {

    Entreprise findByNomEntreprise(String nomEntreprise);

    List<Entreprise> findAllByPays(String idPays);

    List<Entreprise> findAllByRaisonSociale(String idRaisonSociale);

    List<Entreprise> findAllBySecteurActivite(String idSecteurActivite);

    List<Entreprise> findAllBySecteurActiviteAndPays(String idSecteurActivite, String idPays);

    List<Entreprise> findAllBySecteurActiviteAndRaisonSociale(String idSecteurActivite, String idRaisonSociale);

    List<Entreprise> findAllByRaisonSocialeAndPays(String idRaisonSociale, String idPays);

    List<Entreprise> findAllBySecteurActiviteAndRaisonSocialeAndPays(String idSecteurActivite, String idRaisonSociale, String idPays);

    int countByPays(String idPays);

    int countByRaisonSociale(String idRaisonSociale);

    int countBySecteurActivite(String idSecteurActivite);

    int countBySecteurActiviteAndPays(String idSecteurActivite, String idPays);

    int countBySecteurActiviteAndRaisonSociale(String idSecteurActivite, String idRaisonSociale);

    int countByRaisonSocialeAndPays(String idRaisonSociale, String idPays);

    int countBySecteurActiviteAndRaisonSocialeAndPays(String idSecteurActivite, String idRaisonSociale, String idPays);

}
