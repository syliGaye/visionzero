package ci.dcg.visionzero.continent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="continentService")
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public Continent findByLibelleContinent(String libelleContinent) {
        return continentRepository.findByLibelleContinent(libelleContinent);
    }

    @Override
    public Continent getOne(String s) {
        return continentRepository.getOne(s);
    }

    @Override
    public List<Continent> findAll() {
        return continentRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public Continent save(Continent continent) {
        continent.setIdContinent(this.retourneId());
        return continentRepository.save(continent);
    }

    @Override
    public void update(Continent continent) {
        continentRepository.save(continent);
    }

    @Override
    public void delete(String s) {
        continentRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return continentRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "cont"+ft.format(date)+""+i;
    }
}
