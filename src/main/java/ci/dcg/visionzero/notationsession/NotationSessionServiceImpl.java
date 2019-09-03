package ci.dcg.visionzero.notationsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value="notationSessionService")
public class NotationSessionServiceImpl implements NotationSessionService {

    @Autowired
    NotationSessionRepository notationSessionRepository;

    @Override
    public List<NotationSession> findAllBySession(String codeSession) {
        return notationSessionRepository.findAllBySession(codeSession);
    }

    @Override
    public List<NotationSession> findAllByAxe(String codeAxe) {
        return notationSessionRepository.findAllByAxe(codeAxe);
    }

    @Override
    public int countBySession(String codeSession) {
        return this.findAllBySession(codeSession).size();
    }

    @Override
    public int countByAxe(String s) {
        return this.findAllByAxe(s).size();
    }

    @Override
    public NotationSession findBySession(String codeSession) {
        return notationSessionRepository.findBySession(codeSession);
    }

    @Override
    public NotationSession getOne(NotationSessionPK notationSessionPK) {
        return notationSessionRepository.getOne(notationSessionPK);
    }

    @Override
    public List<NotationSession> findAll() {
        return notationSessionRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    public NotationSession save(NotationSession notationSession) {
        return notationSessionRepository.save(notationSession);
    }

    @Override
    public void update(NotationSession notationSession) {
        notationSessionRepository.save(notationSession);
    }

    @Override
    public void delete(NotationSessionPK notationSessionPK) {
        notationSessionRepository.deleteById(notationSessionPK);
    }

    @Override
    public boolean isExiste(NotationSessionPK notationSessionPK) {
        return notationSessionRepository.existsById(notationSessionPK);
    }

    @Override
    public NotationSessionPK retourneId() {
        return null;
    }
}
