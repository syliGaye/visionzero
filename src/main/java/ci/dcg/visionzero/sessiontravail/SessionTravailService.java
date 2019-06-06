package ci.dcg.visionzero.sessiontravail;

import ci.dcg.visionzero.support.ServiceFactory;

public interface SessionTravailService extends ServiceFactory<SessionTravail, String> {
	SessionTravail findByLibelleSession(String sessionTravail);
}
