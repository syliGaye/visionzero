package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.entreprise.Entreprise;
import ci.dcg.visionzero.entreprise.EntrepriseService;
import ci.dcg.visionzero.evaluation.Evaluation;
import ci.dcg.visionzero.evaluation.EvaluationOneList;
import ci.dcg.visionzero.evaluation.EvaluationService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.notationaxe.*;
import ci.dcg.visionzero.notationevaluation.NotationEvaluation;
import ci.dcg.visionzero.notationevaluation.NotationEvaluationService;
import ci.dcg.visionzero.notationquestion.NotationQuestion;
import ci.dcg.visionzero.notationquestion.NotationQuestionService;
import ci.dcg.visionzero.question.Questionnaire;
import ci.dcg.visionzero.question.QuestionnaireOneList;
import ci.dcg.visionzero.question.QuestionnaireService;
import ci.dcg.visionzero.reponse.Reponse;
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
    private NotationQuestionService notationQuestionService;

    @Autowired
    private ReponseService reponseService;

    @Autowired
    private NotationAxeService notationAxeService;

    @Autowired
    private NotationEvaluationService notationEvaluationService;

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

    @GetMapping("regles-or/notation/value/{axe}/{entreprise}")
    ResponseEntity<?> getAxeNotationValue(@PathVariable("axe") String codeAxe,
                                          @PathVariable("entreprise") String codeEntreprise){
        AjaxResponseBody<NotationAxe> ajaxResponseBody = new AjaxResponseBody();
        NotationAxe notationAxe = notationAxeService.findByAxeAndEntreprise(codeAxe, codeEntreprise);

        if (notationAxe == null) {
            notationAxe = new NotationAxe(0.00, axeService.getOne(codeAxe), entrepriseService.getOne(codeEntreprise));
            ajaxResponseBody.setValeur(notationAxeService.save(notationAxe).getValeurNotationAxe());
        }
        else ajaxResponseBody.setValeur(notationAxe.getValeurNotationAxe());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("regles-or/evaluation/value/{evaluation}/{entreprise}")
    ResponseEntity<?> getEvaluationNotationValue(@PathVariable("evaluation") String codeEvaluation,
                                                 @PathVariable("entreprise") String codeEntreprise){
        AjaxResponseBody<NotationEvaluation> ajaxResponseBody = new AjaxResponseBody();
        NotationEvaluation notationEvaluation = notationEvaluationService.findByEvaluationAndEntreprise(codeEvaluation, codeEntreprise);

        if (notationEvaluation == null) {
            notationEvaluation = new NotationEvaluation(0.00, evaluationService.getOne(codeEvaluation), entrepriseService.getOne(codeEntreprise));
            ajaxResponseBody.setValeur(notationEvaluationService.save(notationEvaluation).getValeurNotationEvaluation());
        }
        else ajaxResponseBody.setValeur(notationEvaluation.getValeurNotationEvaluation());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("regles-or/notation/{axe}/{entreprise}/{evaluation}/{question}/{reponse}")
    ResponseEntity<?> noteAndGetValues(@PathVariable("axe") String codeAxe,
                                       @PathVariable("entreprise") String codeEntreprise,
                                       @PathVariable("evaluation") String codeEvaluation,
                                       @PathVariable("question") String codeQuestion,
                                       @PathVariable("reponse") String codeReponse){
        AjaxResponseBody<Map> ajaxResponseBody = new AjaxResponseBody();
        Map<String, Double> map = new HashMap<>();
        Entreprise entreprise = entrepriseService.getOne(codeEntreprise);
        Evaluation evaluation = evaluationService.getOne(codeEvaluation);
        Questionnaire questionnaire = questionnaireService.getOne(codeQuestion);
        Reponse reponse = reponseService.getOne(codeReponse);
        NotationQuestion notationQuestion = notationQuestionService.findByQuestionnaireAndEntreprise(codeQuestion, codeEntreprise);
        double questionValue = 0.0000;
        double evaluationValue = 0.0000;

        if (notationQuestion == null){
            notationQuestion = new NotationQuestion(questionnaire, reponse, entreprise);
            notationQuestionService.save(notationQuestion);
        }
        else {
            notationQuestion.setReponse(reponse);
            notationQuestionService.update(notationQuestion);
        }

        List<Questionnaire> questionnaires = questionnaireService.findAllByEvaluation(evaluation.getCodeEvaluation());

        for(Questionnaire quest : questionnaires){
            NotationQuestion notQuest = notationQuestionService.findByQuestionnaireAndEntreprise(quest.getCodeQuestionnaire(), codeEntreprise);

            if (notQuest == null) notQuest = notationQuestionService.save(new NotationQuestion(quest, reponseService.findByValeur(1), entreprise));

            questionValue += new Double(notQuest.getReponse().getValeurReponse());
        }

        NotationEvaluation notationEvaluation = notationEvaluationService.findByEvaluationAndEntreprise(evaluation.getCodeEvaluation(), codeEntreprise);

        if (notationEvaluation == null) notationEvaluation = notationEvaluationService.save(new NotationEvaluation(new LesFonctions().round(questionValue / questionnaires.size(), 2), evaluation, entreprise));
        else {
            notationEvaluation.setValeurNotationEvaluation(new LesFonctions().round(questionValue / questionnaires.size(), 2));
            notationEvaluationService.update(notationEvaluation);
        }

        List<Evaluation> evaluations = evaluationService.findAllByAxe(codeAxe);

        for (Evaluation eval : evaluations){
            NotationEvaluation notaEval = notationEvaluationService.findByEvaluationAndEntreprise(eval.getCodeEvaluation(), codeEntreprise);

            double valeurProvisoir = 0.0;

            if (notaEval == null) valeurProvisoir = 1.0;
            else valeurProvisoir = notaEval.getValeurNotationEvaluation();

            evaluationValue += valeurProvisoir;
        }

        NotationAxe notationAxe = notationAxeService.findByAxeAndEntreprise(codeAxe, codeEntreprise);

        if (notationAxe == null) notationAxe = notationAxeService.save(new NotationAxe(new LesFonctions().round(evaluationValue / evaluations.size(), 2), axeService.getOne(codeAxe), entreprise));
        else {
            notationAxe.setValeurNotationAxe(new LesFonctions().round(evaluationValue / evaluations.size(), 2));
            notationAxeService.update(notationAxe);
        }

        map.put("valeurAxe", notationAxe.getValeurNotationAxe());
        map.put("valeurEval", notationEvaluation.getValeurNotationEvaluation());
        ajaxResponseBody.setObject(map);

        return ResponseEntity.ok(ajaxResponseBody);
    }
}
