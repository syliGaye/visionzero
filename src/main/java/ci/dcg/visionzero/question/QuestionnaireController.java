package ci.dcg.visionzero.question;

import ci.dcg.visionzero.axe.AxeService;
import ci.dcg.visionzero.entreprise.Entreprise;
import ci.dcg.visionzero.entreprise.EntrepriseService;
import ci.dcg.visionzero.evaluation.EvaluationService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.notationquestion.NotationQuestion;
import ci.dcg.visionzero.notationquestion.NotationQuestionService;
import ci.dcg.visionzero.reponse.Reponse;
import ci.dcg.visionzero.reponse.ReponseService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class QuestionnaireController {
    private static Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private QuestionnaireValidator questionnaireValidator;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private AxeService axeService;
    
    @Autowired
    private NotationQuestionService notationQuestionService;
    
    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private ReponseService reponseService;

    @ModelAttribute("titrepage")
    String titre() {
        return QUESTION_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return QUESTION_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return QUESTION_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return QUESTION_SOUS_MODULE_DEUX;
    }

    @GetMapping("questions")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        model.addAttribute("listQuestions", questionnaireService.findAll());
        return QUESTION_LIST_VIEW_NAME;
    }

    @GetMapping("questions/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listDomaines", axeService.findAll());
        model.addAttribute(new QuestionnaireForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return QUESTION_ADD_VIEW_NAME.concat(" :: questionnaireForm");
        }
        return QUESTION_ADD_VIEW_NAME;
    }

    @PostMapping("questions/add")
    String add(Model model, @Valid @ModelAttribute QuestionnaireForm questionnaireForm, Errors errors){
        questionnaireForm.setEtat(DO_INSERT);
        questionnaireValidator.validate(questionnaireForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listDomaines", axeService.findAll());
            return QUESTION_ADD_VIEW_NAME;
        }

        questionnaireForm.setEvaluation(evaluationService.getOne(questionnaireForm.getCodeEvaluation()));
        Questionnaire questionnaire = questionnaireService.save(questionnaireForm.createNewQuestionnaire());
        List<Entreprise> entreprises = entrepriseService.findAll();
        Reponse reponse = reponseService.findByValeur(1);

        if (!entreprises.isEmpty()){
            for (Entreprise entreprise:entreprises){
                notationQuestionService.save(new NotationQuestion(questionnaire, reponse, entreprise));
            }
        }

        return REDIRECT_QUESTION_LIST;
    }

    @GetMapping("questions/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Questionnaire questionnaire = questionnaireService.getOne(id);

        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listDomaines", axeService.findAll());
        model.addAttribute("id", id);
        model.addAttribute(new QuestionnaireForm(questionnaire.getCodeQuestionnaire(), questionnaire.getLibelleQuestionnaire(), questionnaire.getEvaluation().getCodeEvaluation()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return QUESTION_EDIT_VIEW_NAME.concat(" :: questionnaireForm");
        }
        return QUESTION_EDIT_VIEW_NAME;
    }

    @GetMapping("questions/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute QuestionnaireForm questionnaireForm, Errors errors){
        questionnaireForm.setEtat(DO_UPDATE);
        questionnaireValidator.validate(questionnaireForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listDomaines", axeService.findAll());
            model.addAttribute("id", id);
            return QUESTION_EDIT_VIEW_NAME;
        }

        Questionnaire questionnaire = questionnaireService.getOne(id);
        questionnaire.setEvaluation(evaluationService.getOne(questionnaireForm.getCodeEvaluation()));
        questionnaire.setLibelleQuestionnaire(questionnaireForm.getLibelleQuestionnaire());

        questionnaireService.update(questionnaire);
        return REDIRECT_QUESTION_LIST;
    }

    @GetMapping("questions/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!questionnaireService.isExiste(id)){ajaxResponseBody.setMsg("domaine inexistant!");}
        else {
            questionnaireService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
