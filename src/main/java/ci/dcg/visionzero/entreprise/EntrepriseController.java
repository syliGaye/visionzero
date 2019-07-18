package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.axe.AxeService;
import ci.dcg.visionzero.evaluation.*;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.pays.PaysService;
import ci.dcg.visionzero.raisonsociale.RaisonSocialeService;
import ci.dcg.visionzero.secteuractivite.SecteurActiviteService;
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
public class EntrepriseController {
    private static Logger logger = LoggerFactory.getLogger(EntrepriseController.class);

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private EntrepriseValidator entrepriseValidator;

    @Autowired
    private PaysService paysService;

    @Autowired
    private SecteurActiviteService secteurActiviteService;

    @Autowired
    private RaisonSocialeService raisonSocialeService;

    @ModelAttribute("titrepage")
    String titre() {
        return ENTREPRISE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return ENTREPRISE_MODULE;
    }

    @GetMapping("entreprises")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        model.addAttribute("listEntreprises", entrepriseService.findAll());
        return ENTREPRISE_LIST_VIEW_NAME;
    }

    @GetMapping("entreprises/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listPays", paysService.findAll());
        model.addAttribute("listSecteurActivites", secteurActiviteService.findAll());
        model.addAttribute("listRaisonSociales", raisonSocialeService.findAll());
        model.addAttribute(new EntrepriseForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return ENTREPRISE_ADD_VIEW_NAME.concat(" :: entrepriseForm");
        }
        return ENTREPRISE_ADD_VIEW_NAME;
    }

    @PostMapping("entreprises/add")
    String add(Model model, @Valid @ModelAttribute EntrepriseForm entrepriseForm, Errors errors){
        entrepriseForm.setEtat(DO_INSERT);
        entrepriseValidator.validate(entrepriseForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listPays", paysService.findAll());
            model.addAttribute("listSecteurActivites", secteurActiviteService.findAll());
            model.addAttribute("listRaisonSociales", raisonSocialeService.findAll());
            return ENTREPRISE_ADD_VIEW_NAME;
        }

        entrepriseForm.setPays(paysService.getOne(entrepriseForm.getIdPays()));
        entrepriseForm.setSecteurActivite(secteurActiviteService.getOne(entrepriseForm.getIdSecteurActivite()));
        entrepriseForm.setRaisonSociale(raisonSocialeService.getOne(entrepriseForm.getIdRaisonSociale()));
        entrepriseService.save(entrepriseForm.createNewEntreprise());

        return REDIRECT_ENTREPRISE_LIST;
    }

    @GetMapping("entreprises/get/{id}")
    String get(Model model, @PathVariable("id") String id, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Entreprise entreprise = entrepriseService.getOne(id);

        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listPays", paysService.findAll());
        model.addAttribute("listSecteurActivites", secteurActiviteService.findAll());
        model.addAttribute("listRaisonSociales", raisonSocialeService.findAll());
        model.addAttribute("id", id);
        model.addAttribute(new EntrepriseForm(
                entreprise.getCodeEntreprise(),
                entreprise.getNomEntreprise(),
                entreprise.getDescriptionEntreprise(),
                entreprise.getPays().getIdPays(),
                entreprise.getSecteurActivite().getIdSecteurActivite(),
                entreprise.getRaisonSociale().getIdRaisonSociale()
        ));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return ENTREPRISE_EDIT_VIEW_NAME.concat(" :: entrepriseForm");
        }
        return ENTREPRISE_EDIT_VIEW_NAME;
    }

    @GetMapping("entreprises/edit/{id}")
    String edit(Model model, @PathVariable("id") String id, @Valid @ModelAttribute EntrepriseForm entrepriseForm, Errors errors){
        entrepriseForm.setEtat(DO_UPDATE);
        entrepriseValidator.validate(entrepriseForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listPays", paysService.findAll());
            model.addAttribute("listSecteurActivites", secteurActiviteService.findAll());
            model.addAttribute("listRaisonSociales", raisonSocialeService.findAll());
            model.addAttribute("id", id);
            return ENTREPRISE_EDIT_VIEW_NAME;
        }

        Entreprise entreprise = entrepriseService.getOne(id);
        entreprise.setPays(paysService.getOne(entrepriseForm.getIdPays()));
        entreprise.setSecteurActivite(secteurActiviteService.getOne(entrepriseForm.getIdSecteurActivite()));
        entreprise.setRaisonSociale(raisonSocialeService.getOne(entrepriseForm.getIdRaisonSociale()));
        entreprise.setNomEntreprise(entrepriseForm.getNomEntreprise());
        entreprise.setDescriptionEntreprise(entrepriseForm.getDescriptionEntreprise());

        entrepriseService.update(entreprise);
        return REDIRECT_ENTREPRISE_LIST;
    }

    @GetMapping("entreprises/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!entrepriseService.isExiste(id)){ajaxResponseBody.setMsg("domaine inexistant!");}
        else {
            entrepriseService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
