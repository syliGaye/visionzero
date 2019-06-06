package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.support.ServiceFactory;

public interface UserService extends ServiceFactory<Utilisateur, String> {

    Utilisateur findByLogin(String login);

    Utilisateur findByEmail(String email);

}
