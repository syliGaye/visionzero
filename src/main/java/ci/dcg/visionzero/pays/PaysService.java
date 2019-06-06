package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface PaysService extends ServiceFactory<Pays, String> {

    Pays findByIsoAlphaDeux(String s);

    Pays findByIsoAlphaTrois(String s);

    List<Pays> findAllByContinent(String idContinent);

    int countByContinent(String idContinent);

}
