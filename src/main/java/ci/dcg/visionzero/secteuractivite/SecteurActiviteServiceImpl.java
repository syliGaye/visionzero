package ci.dcg.visionzero.secteuractivite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value = "secteurActiviteService")
public class SecteurActiviteServiceImpl implements SecteurActiviteService {

    @Autowired
    private SecteurActiviteRepository secteurActiviteRepository;

    @Override
    public SecteurActivite getOne(String s) {
        return secteurActiviteRepository.getOne(s);
    }

    @Override
    public List<SecteurActivite> findAll() {
        return secteurActiviteRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public SecteurActivite save(SecteurActivite secteurActivite) {
        secteurActivite.setIdSecteurActivite(this.retourneId());
        return secteurActiviteRepository.save(secteurActivite);
    }

    @Override
    public void update(SecteurActivite secteurActivite) {
        secteurActiviteRepository.save(secteurActivite);
    }

    @Override
    public void delete(String s) {
        secteurActiviteRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return secteurActiviteRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "secAc"+ft.format(date)+""+i;
    }
}
