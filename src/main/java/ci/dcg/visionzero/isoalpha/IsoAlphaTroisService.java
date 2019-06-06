package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.support.ServiceFactory;

public interface IsoAlphaTroisService extends ServiceFactory<IsoAlphaTrois, String> {

    IsoAlphaTrois findByCodeIsoAlphaTrois(String s);

    IsoAlphaTrois findByPays(String idPays);

}
