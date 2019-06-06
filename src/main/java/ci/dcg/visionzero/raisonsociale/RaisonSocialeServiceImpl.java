package ci.dcg.visionzero.raisonsociale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "raisonSocialeService")
public class RaisonSocialeServiceImpl implements RaisonSocialeService {

    @Autowired
    private RaisonSocialeRepository raisonSocialeRepository;

    @Override
    public RaisonSociale getOne(String s) {
        return raisonSocialeRepository.getOne(s);
    }

    @Override
    public List<RaisonSociale> findAll() {
        return raisonSocialeRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public RaisonSociale save(RaisonSociale raisonSociale) {
        raisonSociale.setIdRaisonSociale(this.retourneId());
        return raisonSocialeRepository.save(raisonSociale);
    }

    @Override
    public void update(RaisonSociale raisonSociale) {
        raisonSocialeRepository.save(raisonSociale);
    }

    @Override
    public void delete(String s) {
        raisonSocialeRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return raisonSocialeRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "raiSoc"+ft.format(date)+""+i;
    }
}
