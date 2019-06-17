package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.UtilisateurController;
import ci.dcg.visionzero.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class AxeController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

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
    private AxeValidator axeValidator;

    @ModelAttribute("titrepage")
    String titre() {
        return AXE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return AXE_MODULE;
    }

    @GetMapping("axes")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        List<Axe> axeList = axeService.findAll();

        //fileStorageService.storeFileUser(axeList.get(0).getImageUser());

        for (Axe axe:axeList) {
            fileStorageService.storeFileUser(axe.getImageUser());
        }

        model.addAttribute("listAxes", axeList);
        return AXE_LIST_VIEW_NAME;
    }

    @GetMapping("axes/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listColors", couleurService.findAll());
        model.addAttribute(new AxeForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return AXE_ADD_VIEW_NAME.concat(" :: axeForm");
        }
        return AXE_ADD_VIEW_NAME;
    }


    @PostMapping("axes/add")
    String add(Model model, @Valid @ModelAttribute AxeForm axeForm, Errors errors){
        axeForm.setEtat(DO_INSERT);

        axeValidator.validate(axeForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listColors", couleurService.findAll());
            return AXE_ADD_VIEW_NAME;
        }

        String idAxe = axeService.retourneId();

        try {
            axeForm.setCodeAxe(idAxe);
            axeForm.setImageUser(new LesFonctions().createImageForUser(idAxe, imageUserService, axeForm.getFile()));
            axeForm.setCouleur(couleurService.getOne(axeForm.getIdCouleur()));

            axeService.save(axeForm.createNewAxe());
            return REDIRECT_AXE_LIST;
        } catch (IOException e) {
            e.printStackTrace();
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listColors", couleurService.findAll());
            return AXE_ADD_VIEW_NAME;
        }
    }

    /*@GetMapping("axes/get/{id}")
    String edit(@PathVariable("id") String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Utilisateur utilisateur = userService.getOne(id);
        model.addAttribute(new UtilisateurForm(utilisateur.getId(), utilisateur.getLogin(), utilisateur.getEmail(), utilisateur.getRole().getId()));
        model.addAttribute("id", utilisateur.getId());
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listRole", roleService.findAll());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return USER_EDIT_VIEW_NAME.concat(" :: utilisateurForm");
        }
        return USER_EDIT_VIEW_NAME;
    }

    @GetMapping("axes/edit/{id}")
    String edit(@PathVariable("id") String id, Model model, @Valid @ModelAttribute UtilisateurForm utilisateurForm, Errors errors){
        utilisateurForm.setEtat(DO_UPDATE);
        userValidator.validate(utilisateurForm, errors);

        if (errors.hasErrors()){
            model.addAttribute("id", id);
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listRole", roleService.findAll());

            return USER_EDIT_VIEW_NAME;
        }

        Utilisateur utilisateur = userService.getOne(id);
        Role role = roleService.getOne(utilisateurForm.getIdRole());

        utilisateur.setRole(role);   utilisateur.setLogin(utilisateurForm.getLogin());
        utilisateur.setEmail(utilisateurForm.getEmail());

        userService.update(utilisateur);
        return REDIRECT_USER_LIST;
    }

    @GetMapping("axes/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!userService.isExiste(id)){ajaxResponseBody.setMsg("axe inexistant!");}
        else {
            userService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
    */
}
