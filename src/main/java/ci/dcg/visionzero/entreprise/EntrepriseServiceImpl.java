package ci.dcg.visionzero.entreprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "entrepriseService")
public class EntrepriseServiceImpl implements EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;


    @Override
    public Entreprise findByNomEntreprise(String nomEntreprise) {
        return entrepriseRepository.findByNomEntreprise(nomEntreprise);
    }

    @Override
    public List<Entreprise> findAllByPays(String idPays) {
        return entrepriseRepository.findAllByPays(idPays);
    }

    @Override
    public List<Entreprise> findAllByRaisonSociale(String idRaisonSociale) {
        return entrepriseRepository.findAllByRaisonSociale(idRaisonSociale);
    }

    @Override
    public List<Entreprise> findAllBySecteurActivite(String idSecteurActivite) {
        return entrepriseRepository.findAllBySecteurActivite(idSecteurActivite);
    }

    @Override
    public List<Entreprise> findAllBySecteurActiviteAndPays(String idSecteurActivite, String idPays) {
        return entrepriseRepository.findAllBySecteurActiviteAndPays(idSecteurActivite, idPays);
    }

    @Override
    public List<Entreprise> findAllBySecteurActiviteAndRaisonSociale(String idSecteurActivite, String idRaisonSociale) {
        return entrepriseRepository.findAllBySecteurActiviteAndRaisonSociale(idSecteurActivite, idRaisonSociale);
    }

    @Override
    public List<Entreprise> findAllByRaisonSocialeAndPays(String idRaisonSociale, String idPays) {
        return entrepriseRepository.findAllByRaisonSocialeAndPays(idRaisonSociale, idPays);
    }

    @Override
    public List<Entreprise> findAllBySecteurActiviteAndRaisonSocialeAndPays(String idSecteurActivite, String idRaisonSociale, String idPays) {
        return entrepriseRepository.findAllBySecteurActiviteAndRaisonSocialeAndPays(idSecteurActivite, idRaisonSociale, idPays);
    }

    @Override
    public int countByPays(String idPays) {
        return this.findAllByPays(idPays).size();
    }

    @Override
    public int countByRaisonSociale(String idRaisonSociale) {
        return this.findAllByRaisonSociale(idRaisonSociale).size();
    }

    @Override
    public int countBySecteurActivite(String idSecteurActivite) {
        return this.findAllBySecteurActivite(idSecteurActivite).size();
    }

    @Override
    public int countBySecteurActiviteAndPays(String idSecteurActivite, String idPays) {
        return this.findAllBySecteurActiviteAndPays(idSecteurActivite, idPays).size();
    }

    @Override
    public int countBySecteurActiviteAndRaisonSociale(String idSecteurActivite, String idRaisonSociale) {
        return this.findAllBySecteurActiviteAndRaisonSociale(idSecteurActivite, idRaisonSociale).size();
    }

    @Override
    public int countByRaisonSocialeAndPays(String idRaisonSociale, String idPays) {
        return this.findAllByRaisonSocialeAndPays(idRaisonSociale, idPays).size();
    }

    @Override
    public int countBySecteurActiviteAndRaisonSocialeAndPays(String idSecteurActivite, String idRaisonSociale, String idPays) {
        return this.findAllBySecteurActiviteAndRaisonSocialeAndPays(idSecteurActivite, idRaisonSociale, idPays).size();
    }

    @Override
    public Entreprise getOne(String s) {
        return entrepriseRepository.getOne(s);
    }

    @Override
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Entreprise save(Entreprise entreprise) {
        entreprise.setCodeEntreprise(this.retourneId());
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void update(Entreprise entreprise) {
        entrepriseRepository.save(entreprise);
    }

    @Override
    public void delete(String s) {
        entrepriseRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return entrepriseRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "ent"+ft.format(date)+""+i;
    }
}
