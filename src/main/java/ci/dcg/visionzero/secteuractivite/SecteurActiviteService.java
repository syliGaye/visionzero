package ci.dcg.visionzero.secteuractivite;

import ci.dcg.visionzero.support.ServiceFactory;

public interface SecteurActiviteService extends ServiceFactory<SecteurActivite, String> {
    SecteurActivite findByLibelleSecteurActivite(String s);
}
