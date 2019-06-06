package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.support.ServiceFactory;

public interface IsoAlphaDeuxService extends ServiceFactory<IsoAlphaDeux, String> {

    IsoAlphaDeux findByPays(String s);

    IsoAlphaDeux findByCodeIsoAlphaDeux(String s);

}
