package ci.dcg.visionzero.reponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="reponseService")
public class ReponseServiceImpl implements ReponseService {

    @Autowired
    private ReponseRepository reponseRepository;

    @Override
    public Reponse findByLibelle(String libelle) {
        return reponseRepository.findByLibelleReponse(libelle);
    }

    @Override
    public Reponse findByValeur(Integer valeur) {
        return reponseRepository.findByValeurReponse(valeur);
    }

    @Override
    public boolean isReponseExistByLibelle(String libelle) {
        return this.findByLibelle(libelle) != null;
    }

    @Override
    public boolean isReponseExistByValeur(Integer valeur) {
        return this.findByValeur(valeur) != null;
    }

    @Override
    public void deleteReponse(Reponse reponse) {
        reponseRepository.delete(reponse);
    }

    @Override
    public Reponse getOne(String s) {
        return reponseRepository.getOne(s);
    }

    @Override
    public List<Reponse> findAll() {
        return reponseRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Reponse save(Reponse reponse) {
        reponse.setCodeReponse(this.retourneId());
        return reponseRepository.save(reponse);
    }

    @Override
    public void update(Reponse reponse) {
        reponseRepository.save(reponse);
    }

    @Override
    public void delete(String s) {
        reponseRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return reponseRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "rep"+ft.format(date)+""+i;
    }
}
