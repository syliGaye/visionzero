package ci.dcg.visionzero.continent;

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
public class ContinentController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private ContinentService continentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ContinentValidator continentValidator;

    @ModelAttribute("titrepage")
    String titre() {
        return CONTINENT_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return CONTINENT_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return CONTINENT_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return CONTINENT_SOUS_MODULE_DEUX;
    }

    @GetMapping("continents")
    String continents(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listContinent", continentService.findAll());
        return CONTINENT_LIST_VIEW_NAME;
    }

    @GetMapping("continents/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute(new ContinentForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return CONTINENT_ADD_VIEW_NAME.concat(" :: continentForm");
        }
        return CONTINENT_ADD_VIEW_NAME;
    }

    @PostMapping("continents/add")
    String add(Model model, @Valid @ModelAttribute ContinentForm continentForm, Errors errors){
        continentValidator.validate(continentForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return CONTINENT_ADD_VIEW_NAME;
        }

        continentService.save(continentForm.createNewContinent());
        return REDIRECT_CONTINENT_LIST;
    }

    @GetMapping("continents/get/{id}")
    String edit(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        Continent continent = continentService.getOne(id);

        model.addAttribute("id", id);
        model.addAttribute(new ContinentForm(continent.getIdContinent(), continent.getLibelleContinent()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return CONTINENT_EDIT_VIEW_NAME.concat(" :: continentForm");
        }
        return CONTINENT_EDIT_VIEW_NAME;
    }

    @GetMapping("continents/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute ContinentForm continentForm, Errors errors){
        continentValidator.validate(continentForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("id", id);

            return CONTINENT_EDIT_VIEW_NAME;
        }

        Continent continent = continentService.getOne(id);
        continent.setLibelleContinent(continentForm.getLibelleContinent());

        continentService.update(continent);
        return REDIRECT_CONTINENT_LIST;
    }

    @GetMapping("continents/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!continentService.isExiste(id)){ajaxResponseBody.setMsg("continent inexistant!");}
        else {
            continentService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
