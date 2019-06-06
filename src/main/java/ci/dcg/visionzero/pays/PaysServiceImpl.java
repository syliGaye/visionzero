package ci.dcg.visionzero.pays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "paysService")
public class PaysServiceImpl implements PaysService {

    @Autowired
    private PaysRepository paysRepository;

    @Override
    public Pays findByIsoAlphaDeux(String s) {
        return paysRepository.findByIsoAlphaDeux(s);
    }

    @Override
    public Pays findByIsoAlphaTrois(String s) {
        return paysRepository.findByIsoAlphaTrois(s);
    }

    @Override
    public List<Pays> findAllByContinent(String idContinent) {
        return paysRepository.findAllByContinent(idContinent);
    }

    @Override
    public int countByContinent(String idContinent) {
        return this.findAllByContinent(idContinent).size();
    }

    @Override
    public Pays getOne(String s) {
        return paysRepository.getOne(s);
    }

    @Override
    public List<Pays> findAll() {
        return paysRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Pays save(Pays pays) {
        pays.setIdPays(this.retourneId());
        return null;
    }

    @Override
    public void update(Pays pays) {
        paysRepository.save(pays);
    }

    @Override
    public void delete(String s) {
        paysRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return paysRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "pays"+ft.format(date)+""+i;
    }
}
