package ci.dcg.visionzero.continent;

import ci.dcg.visionzero.support.ServiceFactory;

public interface ContinentService extends ServiceFactory<Continent, String> {
    Continent findByLibelleContinent(String libelleContinent);
}
