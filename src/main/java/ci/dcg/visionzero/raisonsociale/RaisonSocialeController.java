package ci.dcg.visionzero.raisonsociale;

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
public class RaisonSocialeController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private RaisonSocialeService raisonSocialeService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private RaisonSocialeValidator raisonSocialeValidator;

    @ModelAttribute("titrepage")
    String titre() {
        return RAISON_SOCIALE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return RAISON_SOCIALE_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return RAISON_SOCIALE_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return RAISON_SOCIALE_SOUS_MODULE_DEUX;
    }

    @GetMapping("raisonsociales")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listRaisonSociale", raisonSocialeService.findAll());
        return RAISON_SOCIALE_LIST_VIEW_NAME;
    }

    @GetMapping("raisonsociales/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute(new RaisonSocialeForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return RAISON_SOCIALE_ADD_VIEW_NAME.concat(" :: raisonSocialeForm");
        }
        return RAISON_SOCIALE_ADD_VIEW_NAME;
    }

    @PostMapping("raisonsociales/add")
    String add(Model model, @Valid @ModelAttribute RaisonSocialeForm raisonSocialeForm, Errors errors){
        raisonSocialeValidator.validate(raisonSocialeForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return RAISON_SOCIALE_ADD_VIEW_NAME;
        }

        raisonSocialeService.save(raisonSocialeForm.createNewRaisonSociale());
        return REDIRECT_RAISON_SOCIALE_ACTIVITE_LIST;
    }

    @GetMapping("raisonsociales/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        RaisonSociale raisonSociale = raisonSocialeService.getOne(id);

        model.addAttribute("id", id);
        model.addAttribute(new RaisonSocialeForm(raisonSociale.getIdRaisonSociale(), raisonSociale.getLibelleRaisonSociale()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return RAISON_SOCIALE_EDIT_VIEW_NAME.concat(" :: raisonSocialeForm");
        }
        return RAISON_SOCIALE_EDIT_VIEW_NAME;
    }

    @GetMapping("raisonsociales/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute RaisonSocialeForm raisonSocialeForm, Errors errors){
        raisonSocialeValidator.validate(raisonSocialeForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("id", id);

            return RAISON_SOCIALE_EDIT_VIEW_NAME;
        }

        RaisonSociale raisonSociale = raisonSocialeService.getOne(id);
        raisonSociale.setLibelleRaisonSociale(raisonSocialeForm.getLibelleRaisonSociale());

        raisonSocialeService.update(raisonSociale);
        return REDIRECT_RAISON_SOCIALE_ACTIVITE_LIST;
    }

    @GetMapping("raisonsociales/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!raisonSocialeService.isExiste(id)){ajaxResponseBody.setMsg("raison sociale inexistant!");}
        else {
            raisonSocialeService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
