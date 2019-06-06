package ci.dcg.visionzero.notationaxe;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;

public interface NotationAxeService  extends ServiceFactory<NotationAxe, String> {

	List<NotationAxe> findAllByAxe(String codeAxe);

	int countByAxe(String codeAxe);

	NotationAxe findByAxe(String codeAxe);

}
