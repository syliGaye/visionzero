package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.continent.Continent;
import ci.dcg.visionzero.continent.ContinentService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.isoalpha.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class PaysController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private IsoAlphaTroisService alphaTroisService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private IsoAlphaDeuxService alphaDeuxService;

    @Autowired
    private PaysService paysService;

    @Autowired
    private PaysValidator paysValidator;

    @Autowired
    private ContinentService continentService;

    @ModelAttribute("titrepage")
    String titre() {
        return PAYS_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return PAYS_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return PAYS_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return PAYS_SOUS_MODULE_DEUX;
    }

    @GetMapping("pays")
    String pays(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        List<Pays> paysList = paysService.findAll();
        List<Map> mapList = new ArrayList<>();

        for (Pays pays : paysList) {
            Map<String, String> map = new HashMap<>();
            IsoAlphaDeux alphaDeux = alphaDeuxService.findByPays(pays.getIdPays());
            IsoAlphaTrois alphaTrois = alphaTroisService.findByPays(pays.getIdPays());

            map.put("id", pays.getIdPays());   map.put("pays", pays.getLibellePays());   map.put("continent", pays.getContinent().getLibelleContinent());
            map.put("alphadeux", alphaDeux.getCodeIsoAlphaDeux());   map.put("alphatrois", alphaTrois.getCodeIsoAlphaTrois());
            mapList.add(map);
        }

        model.addAttribute("listPays", mapList);
        return PAYS_LIST_VIEW_NAME;
    }

    @GetMapping("pays/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listContinent", continentService.findAll());
        model.addAttribute(new PaysForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return PAYS_ADD_VIEW_NAME.concat(" :: paysForm");
        }
        return PAYS_ADD_VIEW_NAME;
    }

    @PostMapping("pays/add")
    String add(Model model, @Valid @ModelAttribute PaysForm paysForm, Errors errors){
        paysForm.setEtat(DO_INSERT);
        paysValidator.validate(paysForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listContinent", continentService.findAll());
            return PAYS_ADD_VIEW_NAME;
        }

        Continent continent = continentService.getOne(paysForm.getIdContinent());
        paysForm.setContinent(continent);
        Pays pays = paysService.save(paysForm.createNewPays());

        IsoAlphaDeuxForm alphaDeuxForm = new IsoAlphaDeuxForm(paysForm.getIsoAlphaDeux(), pays);
        IsoAlphaTroisForm alphaTroisForm = new IsoAlphaTroisForm(paysForm.getIsoAlphaTrois(), pays);

        alphaDeuxService.save(alphaDeuxForm.createNewIsoAlphaDeux());
        alphaTroisService.save(alphaTroisForm.createNewIsoAlphaTrois());
        return REDIRECT_PAYS_LIST;
    }

    @GetMapping("pays/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Pays pays = paysService.getOne(id);
        IsoAlphaDeux alphaDeux = alphaDeuxService.findByPays(pays.getIdPays());
        IsoAlphaTrois alphaTrois = alphaTroisService.findByPays(pays.getIdPays());

        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listContinent", continentService.findAll());
        model.addAttribute("id", id);
        model.addAttribute(new PaysForm(pays.getIdPays(), pays.getLibellePays(), alphaDeux.getCodeIsoAlphaDeux(), alphaTrois.getCodeIsoAlphaTrois(), pays.getContinent().getIdContinent()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return PAYS_EDIT_VIEW_NAME.concat(" :: paysForm");
        }
        return PAYS_EDIT_VIEW_NAME;
    }

    @GetMapping("pays/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute PaysForm paysForm, Errors errors){
        paysForm.setEtat(DO_UPDATE);
        paysValidator.validate(paysForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listContinent", continentService.findAll());
            model.addAttribute("id", id);
            return PAYS_EDIT_VIEW_NAME;
        }

        Continent continent = continentService.getOne(paysForm.getIdContinent());
        Pays pays = paysService.getOne(id);
        pays.setContinent(continent);   pays.setLibellePays(paysForm.getLibellePays());
        paysService.update(pays);

        IsoAlphaDeux alphaDeux = alphaDeuxService.findByPays(pays.getIdPays());
        alphaDeux.setCodeIsoAlphaDeux(paysForm.getIsoAlphaDeux());   alphaDeux.setPays(pays);

        IsoAlphaTrois alphaTrois = alphaTroisService.findByPays(pays.getIdPays());
        alphaTrois.setCodeIsoAlphaTrois(paysForm.getIsoAlphaTrois());   alphaTrois.setPays(pays);

        alphaDeuxService.update(alphaDeux);
        alphaTroisService.update(alphaTrois);
        return REDIRECT_PAYS_LIST;
    }

    @GetMapping("pays/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!paysService.isExiste(id)){ajaxResponseBody.setMsg("pays inexistant!");}
        else {
            alphaDeuxService.delete(alphaDeuxService.findByPays(id).getIdIsoAlphaDeux());
            alphaTroisService.delete(alphaTroisService.findByPays(id).getIdIsoAlphaTrois());
            paysService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
