package ci.dcg.visionzero.isoalpha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "isoAlphaTroisService")
public class IsoAlphaTroisServiceImpl implements IsoAlphaTroisService {

    @Autowired
    private IsoAlphaTroisRepository isoAlphaTroisRepository;

    @Override
    public IsoAlphaTrois findByCodeIsoAlphaTrois(String s) {
        return isoAlphaTroisRepository.findByCodeIsoAlphaTrois(s);
    }

    @Override
    public IsoAlphaTrois findByPays(String idPays) {
        return isoAlphaTroisRepository.findByPays(idPays);
    }

    @Override
    public IsoAlphaTrois getOne(String s) {
        return isoAlphaTroisRepository.getOne(s);
    }

    @Override
    public List<IsoAlphaTrois> findAll() {
        return isoAlphaTroisRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public IsoAlphaTrois save(IsoAlphaTrois isoAlphaTrois) {
        isoAlphaTrois.setIdIsoAlphaTrois(this.retourneId());
        return isoAlphaTroisRepository.save(isoAlphaTrois);
    }

    @Override
    public void update(IsoAlphaTrois isoAlphaTrois) {
        isoAlphaTroisRepository.save(isoAlphaTrois);
    }

    @Override
    public void delete(String s) {
        isoAlphaTroisRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return isoAlphaTroisRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "isotr"+ft.format(date)+""+i;
    }
}
