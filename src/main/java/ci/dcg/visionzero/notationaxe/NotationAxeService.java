package ci.dcg.visionzero.notationaxe;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationAxeService  extends ServiceFactory<NotationAxe, String> {

	List<NotationAxe> findAllByAxe(String codeAxe);

	List<NotationAxe> findAllByEntreprise(String codeEntreprise);

	NotationAxe findByAxeAndEntreprise(String codeAxe, String codeEntreprise);

	int countByAxe(String codeAxe);

	int countByEntreprise(String codeEntreprise);

}
