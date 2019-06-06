package ci.dcg.visionzero.sessiontravail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="sessionTravailService")
public class SessionTravailServiceImpl implements SessionTravailService {

    @Autowired
    SessionTravailRepository sessionTravailRepository;

    @Override
    public SessionTravail findByLibelleSession(String sessionTravail) {
        return sessionTravailRepository.findByLibelleSession(sessionTravail);
    }

    @Override
    public SessionTravail getOne(String s) {
        return sessionTravailRepository.getOne(s);
    }

    @Override
    public List<SessionTravail> findAll() {
        return sessionTravailRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public SessionTravail save(SessionTravail sessionTravail) {
        sessionTravail.setCodeSession(this.retourneId());
        return sessionTravailRepository.save(sessionTravail);
    }

    @Override
    public void update(SessionTravail sessionTravail) {
        sessionTravailRepository.save(sessionTravail);
    }

    @Override
    public void delete(String s) {
        sessionTravailRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return sessionTravailRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "sess"+ft.format(date)+""+i;
    }
}
