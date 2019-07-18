package ci.dcg.visionzero.reponse;

import ci.dcg.visionzero.axe.AxeService;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.evaluation.EvaluationService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.question.*;
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

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class ReponseController {
    private static Logger logger = LoggerFactory.getLogger(ReponseController.class);

    @Autowired
    private ReponseService reponseService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ReponseValidator reponseValidator;

    @Autowired
    private CouleurService couleurService;

    @ModelAttribute("titrepage")
    String titre() {
        return REPONSE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return REPONSE_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return REPONSE_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return REPONSE_SOUS_MODULE_DEUX;
    }

    @GetMapping("reponses")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        model.addAttribute("listReponses", reponseService.findAll());
        return REPONSE_LIST_VIEW_NAME;
    }

    @GetMapping("reponses/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listCouleurs", couleurService.findAll());
        model.addAttribute(new ReponseForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return REPONSE_ADD_VIEW_NAME.concat(" :: reponseForm");
        }
        return REPONSE_ADD_VIEW_NAME;
    }

    @PostMapping("reponses/add")
    String add(Model model, @Valid @ModelAttribute ReponseForm reponseForm, Errors errors){
        reponseForm.setEtat(DO_INSERT);
        reponseValidator.validate(reponseForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listCouleurs", couleurService.findAll());
            return REPONSE_ADD_VIEW_NAME;
        }

        reponseForm.setCouleur(couleurService.getOne(reponseForm.getIdCouleur()));
        reponseService.save(reponseForm.createNewReponse());

        return REDIRECT_REPONSE_LIST;
    }

    @GetMapping("reponses/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Reponse reponse = reponseService.getOne(id);

        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listCouleurs", couleurService.findAll());
        model.addAttribute("id", id);
        model.addAttribute(new ReponseForm(reponse.getCodeReponse(), reponse.getLibelleReponse(), reponse.getValeurReponse(), reponse.getCouleur().getCodeCouleur()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return REPONSE_EDIT_VIEW_NAME.concat(" :: reponseForm");
        }
        return REPONSE_EDIT_VIEW_NAME;
    }

    @GetMapping("reponses/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute ReponseForm reponseForm, Errors errors){
        reponseForm.setEtat(DO_UPDATE);
        reponseValidator.validate(reponseForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listCouleurs", couleurService.findAll());
            model.addAttribute("id", id);
            return REPONSE_EDIT_VIEW_NAME;
        }

        Reponse reponse = reponseService.getOne(id);
        reponse.setCouleur(couleurService.getOne(reponseForm.getIdCouleur()));
        reponse.setLibelleReponse(reponseForm.getLibelleReponse());
        reponse.setValeurReponse(reponseForm.getValeurReponse());

        reponseService.update(reponse);
        return REDIRECT_REPONSE_LIST;
    }

    @GetMapping("reponses/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!reponseService.isExiste(id)){ajaxResponseBody.setMsg("reponse inexistante!");}
        else {
            reponseService.deleteReponse(reponseService.getOne(id));
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
