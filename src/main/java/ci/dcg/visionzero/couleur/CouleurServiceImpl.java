package ci.dcg.visionzero.couleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "couleurService")
public class CouleurServiceImpl implements CouleurService {

    @Autowired
    private CouleurRepository couleurRepository;

    @Override
    public Couleur findCouleurByLibelleCouleur(String libelleCouleur) {
        return couleurRepository.findByLibelleCouleur(libelleCouleur);
    }

    @Override
    public Couleur getOne(String s) {
        return couleurRepository.getOne(s);
    }

    @Override
    public List<Couleur> findAll() {
        return couleurRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Couleur save(Couleur couleur) {
        couleur.setCodeCouleur(this.retourneId());
        return couleurRepository.save(couleur);
    }

    @Override
    public void update(Couleur couleur) {
        couleurRepository.save(couleur);
    }

    @Override
    public void delete(String s) {
        couleurRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return couleurRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "coul"+ft.format(date)+""+i;
    }
}
