package ci.dcg.visionzero.image;

import ci.dcg.visionzero.support.ServiceFactory;

public interface ImageService extends ServiceFactory<Image, String> {
    Image findByAxe(String codeAxe);
}
