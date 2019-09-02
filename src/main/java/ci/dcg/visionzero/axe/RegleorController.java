package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.entreprise.EntrepriseService;
import ci.dcg.visionzero.evaluation.Evaluation;
import ci.dcg.visionzero.evaluation.EvaluationOneList;
import ci.dcg.visionzero.evaluation.EvaluationService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.notationevaluation.NotationEvaluationInd;
import ci.dcg.visionzero.notationquestion.NotationQuestionInd;
import ci.dcg.visionzero.notationquestion.NotationQuestionIndService;
import ci.dcg.visionzero.question.Questionnaire;
import ci.dcg.visionzero.question.QuestionnaireOneList;
import ci.dcg.visionzero.question.QuestionnaireService;
import ci.dcg.visionzero.reponse.ReponseService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class RegleorController {
    private static Logger logger = LoggerFactory.getLogger(RegleorController.class);

    @Autowired
    private AxeService axeService;

    @Autowired
    private CouleurService couleurService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageUserService imageUserService;

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private NotationQuestionIndService notationQuestionIndService;

    @Autowired
    private ReponseService reponseService;

    @ModelAttribute("titrepage")
    String titre() {
        return REGLE_OR_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return REGLE_OR_MODULE;
    }

    @GetMapping("regles-or")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        List<Axe> axeList = axeService.findAll();

        if (!axeList.isEmpty()){
            for (Axe axe:axeList) {
                fileStorageService.storeFileUser(axe.getImageUser());
            }
        }

        model.addAttribute("lesAxes", axeList);
        return REGLE_OR_LIST_VIEW_NAME;
    }

    @GetMapping("regles-or/notation/{axe}/{entreprise}")
    String getOneAxeWithChildren(Model model, @PathVariable("axe") String codeAxe, @PathVariable("entreprise") String codeEntreprise){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        AxeOneList axeOneList = new AxeOneList();
        List<EvaluationOneList> evaluationOneLists = new ArrayList<>();
        Axe axe = axeService.getOne(codeAxe);
        List<Evaluation> evaluations = evaluationService.findAllByAxe(axe.getCodeAxe());

        for (Evaluation evaluation : evaluations) {
            EvaluationOneList evaluationOneList = new EvaluationOneList();
            List<QuestionnaireOneList> questionnaireOneLists = new ArrayList<>();
            List<Questionnaire> questionnaires = questionnaireService.findAllByEvaluation(evaluation.getCodeEvaluation());

            for (Questionnaire questionnaire:questionnaires) {
                QuestionnaireOneList questionnaireOneList = new QuestionnaireOneList();
                questionnaireOneList.setCodeQuestionnaire(questionnaire.getCodeQuestionnaire());
                questionnaireOneList.setLibelleQuestionnaire(questionnaire.getLibelleQuestionnaire());
                questionnaireOneLists.add(questionnaireOneList);
            }

            evaluationOneList.setCodeEvaluation(evaluation.getCodeEvaluation());
            evaluationOneList.setLibelleEvaluation(evaluation.getLibelleEvaluation());
            evaluationOneList.setQuestionnaireOneLists(questionnaireOneLists);
            evaluationOneLists.add(evaluationOneList);
        }

        axeOneList.setCodeAxe(axe.getCodeAxe());  axeOneList.setDescriptionAxe(axe.getDescriptionAxe());
        axeOneList.setLibelleAxe(axe.getLibelleAxe());  axeOneList.setEvaluationOneLists(evaluationOneLists);
        axeOneList.setCouleur(axe.getCouleur());  axeOneList.setImageUser(axe.getImageUser());
        fileStorageService.storeFileUser(axeOneList.getImageUser());

        model.addAttribute("unAxe", axeOneList);
        model.addAttribute("nbreEvaluation", axeOneList.getEvaluationOneLists().size());
        model.addAttribute("uneEntreprise", entrepriseService.getOne(codeEntreprise));
        model.addAttribute("lesReponses", reponseService.findAll());

        return REGLE_OR_ONE_VIEW_NAME;
    }
}
