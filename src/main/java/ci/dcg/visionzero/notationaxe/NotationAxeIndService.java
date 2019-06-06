package ci.dcg.visionzero.notationaxe;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationAxeIndService  extends ServiceFactory<NotationAxeInd, String> {

    List<NotationAxeInd> findAllByAxe(String codeAxe);

    List<NotationAxeInd> findAllByEntreprise(String codeEntreprise);

    int countByAxe(String codeAxe);

    int countByEntreprise(String codeEntreprise);

    NotationAxeInd findByAxeAndEntreprise(String codeAxe, String codeEntreprise);

}
