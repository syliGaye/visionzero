package ci.dcg.visionzero.evaluation;

import ci.dcg.visionzero.axe.AxeService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.UtilisateurController;
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

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class EvaluationController {
    private static Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private EvaluationValidator evaluationValidator;

    @Autowired
    private AxeService axeService;

    @ModelAttribute("titrepage")
    String titre() {
        return DOMAINE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return DOMAINE_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return DOMAINE_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return DOMAINE_SOUS_MODULE_DEUX;
    }

    @GetMapping("domaines")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        model.addAttribute("listDomaines", evaluationService.findAll());
        return DOMAINE_LIST_VIEW_NAME;
    }

    @GetMapping("domaines/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listAxes", axeService.findAll());
        model.addAttribute(new EvaluationForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return DOMAINE_ADD_VIEW_NAME.concat(" :: evaluationForm");
        }
        return DOMAINE_ADD_VIEW_NAME;
    }

    @PostMapping("domaines/add")
    String add(Model model, @Valid @ModelAttribute EvaluationForm evaluationForm, Errors errors){
        evaluationForm.setEtat(DO_INSERT);
        evaluationValidator.validate(evaluationForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listAxes", axeService.findAll());
            return DOMAINE_ADD_VIEW_NAME;
        }

        evaluationForm.setAxe(axeService.getOne(evaluationForm.getIdAxe()));
        evaluationService.save(evaluationForm.createNewEvaluation());

        return REDIRECT_DOMAINE_LIST;
    }

    @GetMapping("domaines/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Evaluation evaluation = evaluationService.getOne(id);

        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listAxes", axeService.findAll());
        model.addAttribute("id", id);
        model.addAttribute(new EvaluationForm(evaluation.getCodeEvaluation(), evaluation.getLibelleEvaluation(), evaluation.getAxe().getCodeAxe()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return DOMAINE_EDIT_VIEW_NAME.concat(" :: evaluationForm");
        }
        return DOMAINE_EDIT_VIEW_NAME;
    }

    @GetMapping("domaines/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute EvaluationForm evaluationForm, Errors errors){
        evaluationForm.setEtat(DO_UPDATE);
        evaluationValidator.validate(evaluationForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listAxes", axeService.findAll());
            model.addAttribute("id", id);
            return DOMAINE_EDIT_VIEW_NAME;
        }

        Evaluation evaluation = evaluationService.getOne(id);
        evaluation.setAxe(axeService.getOne(evaluationForm.getIdAxe()));
        evaluation.setLibelleEvaluation(evaluationForm.getLibelleEvaluation());

        evaluationService.update(evaluation);
        return REDIRECT_DOMAINE_LIST;
    }

    @GetMapping("domaines/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!evaluationService.isExiste(id)){ajaxResponseBody.setMsg("domaine inexistant!");}
        else {
            evaluationService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
