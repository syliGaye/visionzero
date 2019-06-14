package ci.dcg.visionzero.secteuractivite;

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
public class SecteurActiviteController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private SecteurActiviteService secteurActiviteService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SecteurActiviteValidator secteurActiviteValidator;

    @ModelAttribute("titrepage")
    String titre() {
        return SECTEUR_ACTIVITE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return SECTEUR_ACTIVITE_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return SECTEUR_ACTIVITE_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return SECTEUR_ACTIVITE_SOUS_MODULE_DEUX;
    }

    @GetMapping("secteuractivites")
    String continents(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listSecteurActivite", secteurActiviteService.findAll());
        return SECTEUR_ACTIVITE_LIST_VIEW_NAME;
    }

    @GetMapping("secteuractivites/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute(new SecteurActiviteForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SECTEUR_ACTIVITE_ADD_VIEW_NAME.concat(" :: secteurActiviteForm");
        }
        return SECTEUR_ACTIVITE_ADD_VIEW_NAME;
    }

    @PostMapping("secteuractivites/add")
    String add(Model model, @Valid @ModelAttribute SecteurActiviteForm secteurActiviteForm, Errors errors){
        secteurActiviteValidator.validate(secteurActiviteForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return SECTEUR_ACTIVITE_ADD_VIEW_NAME;
        }

        secteurActiviteService.save(secteurActiviteForm.createNewSecteurActivite());
        return REDIRECT_SECTEUR_ACTIVITE_LIST;
    }

    @GetMapping("secteuractivites/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        SecteurActivite secteurActivite = secteurActiviteService.getOne(id);

        model.addAttribute("id", id);
        model.addAttribute(new SecteurActiviteForm(secteurActivite.getIdSecteurActivite(), secteurActivite.getLibelleSecteurActivite()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SECTEUR_ACTIVITE_EDIT_VIEW_NAME.concat(" :: secteurActiviteForm");
        }
        return SECTEUR_ACTIVITE_EDIT_VIEW_NAME;
    }

    @GetMapping("secteuractivites/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute SecteurActiviteForm secteurActiviteForm, Errors errors){
        secteurActiviteValidator.validate(secteurActiviteForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("id", id);

            return SECTEUR_ACTIVITE_EDIT_VIEW_NAME;
        }

        SecteurActivite secteurActivite = secteurActiviteService.getOne(id);
        secteurActivite.setLibelleSecteurActivite(secteurActiviteForm.getLibelleSecteurActivite());

        secteurActiviteService.update(secteurActivite);
        return REDIRECT_SECTEUR_ACTIVITE_LIST;
    }

    @GetMapping("secteuractivites/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!secteurActiviteService.isExiste(id)){ajaxResponseBody.setMsg("secteur activité inexistant!");}
        else {
            secteurActiviteService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
