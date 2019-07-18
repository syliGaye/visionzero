package ci.dcg.visionzero.reponse;

import ci.dcg.visionzero.support.ServiceFactory;

public interface ReponseService extends ServiceFactory<Reponse, String> {

	Reponse findByLibelle(String libelle);

	Reponse findByValeur(Integer valeur);

	boolean isReponseExistByLibelle(String libelle);

	boolean isReponseExistByValeur(Integer valeur);

	void deleteReponse(Reponse reponse);
}
