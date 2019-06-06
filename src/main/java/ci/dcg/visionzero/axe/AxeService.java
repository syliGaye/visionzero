package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.List;


public interface AxeService extends ServiceFactory<Axe, String> {

	Axe findByQuestionnaire(String codeQuestion);

	List<Axe> findAllByQuestion();

	int countByQuestion();

}
