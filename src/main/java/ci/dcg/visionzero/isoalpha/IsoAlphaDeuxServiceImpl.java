package ci.dcg.visionzero.isoalpha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "isoAlphaDeuxService")
public class IsoAlphaDeuxServiceImpl implements IsoAlphaDeuxService {

    @Autowired
    private IsoAlphaDeuxRepository isoAlphaDeuxRepository;

    @Override
    public IsoAlphaDeux findByPays(String idPays) {
        return isoAlphaDeuxRepository.findByPays(idPays);
    }

    @Override
    public IsoAlphaDeux findByCodeIsoAlphaDeux(String s) {
        return isoAlphaDeuxRepository.findByCodeIsoAlphaDeux(s);
    }

    @Override
    public IsoAlphaDeux getOne(String s) {
        return isoAlphaDeuxRepository.getOne(s);
    }

    @Override
    public List<IsoAlphaDeux> findAll() {
        return isoAlphaDeuxRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public IsoAlphaDeux save(IsoAlphaDeux isoAlphaDeux) {
        isoAlphaDeux.setIdIsoAlphaDeux(this.retourneId());
        return isoAlphaDeuxRepository.save(isoAlphaDeux);
    }

    @Override
    public void update(IsoAlphaDeux isoAlphaDeux) {
        isoAlphaDeuxRepository.save(isoAlphaDeux);
    }

    @Override
    public void delete(String s) {
        isoAlphaDeuxRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return isoAlphaDeuxRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "isodx"+ft.format(date)+""+i;
    }
}
