package ci.dcg.visionzero.raisonsociale;

import ci.dcg.visionzero.support.ServiceFactory;

public interface RaisonSocialeService extends ServiceFactory<RaisonSociale, String> {
    RaisonSociale findByLibelleRaisonSociale(String s);
}
