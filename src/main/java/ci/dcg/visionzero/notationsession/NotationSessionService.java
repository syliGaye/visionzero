package ci.dcg.visionzero.notationsession;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationSessionService extends ServiceFactory<NotationSession, NotationSessionPK> {

	List<NotationSession> findAllBySession(String codeSession);

	List<NotationSession> findAllByAxe(String codeAxe);

	int countBySession(String codeSession);

	int countByAxe(String s);

	NotationSession findBySession(String codeSession);

}
