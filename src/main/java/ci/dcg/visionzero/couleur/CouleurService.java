package ci.dcg.visionzero.couleur;

import ci.dcg.visionzero.support.ServiceFactory;

public interface CouleurService extends ServiceFactory<Couleur, String> {
    Couleur findCouleurByLibelleCouleur(String libelleCouleur);
}
