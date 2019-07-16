package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.files.FileForm;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUser;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.UtilisateurController;
import ci.dcg.visionzero.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

        if (!axeList.isEmpty()){
            for (Axe axe:axeList) {
                fileStorageService.storeFileUser(axe.getImageUser());
            }
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

    @GetMapping("axes/get/{id}")
    String get(@PathVariable("id") String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Axe axe = axeService.getOne(id);

        model.addAttribute(new AxeForm(axe.getCodeAxe(), axe.getLibelleAxe(), axe.getDescriptionAxe(), axe.getCouleur().getCodeCouleur()));
        model.addAttribute("id", axe.getCodeAxe());
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listColors", couleurService.findAll());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return AXE_EDIT_VIEW_NAME.concat(" :: axeForm");
        }
        return AXE_EDIT_VIEW_NAME;
    }

    @GetMapping("axes/file/get/{id}")
    String getFile(@PathVariable("id") String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        Axe axe = axeService.getOne(id);

        model.addAttribute(new FileForm());
        model.addAttribute("id", axe.getCodeAxe());
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return AXE_EDIT_FILE_VIEW_NAME.concat(" :: fileForm");
        }
        return AXE_EDIT_FILE_VIEW_NAME;
    }

    @GetMapping("axes/edit/{id}")
    String edit(@PathVariable("id") String id, Model model, @Valid @ModelAttribute AxeForm axeForm, Errors errors){
        axeForm.setEtat(DO_UPDATE);
        axeValidator.validate(axeForm, errors);

        if (errors.hasErrors()){
            model.addAttribute("id", id);
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listColors", couleurService.findAll());

            return AXE_EDIT_VIEW_NAME;
        }

        Axe axe = axeService.getOne(id);
        axe.setCouleur(couleurService.getOne(axeForm.getIdCouleur()));   axe.setLibelleAxe(axeForm.getLibelleAxe());
        axe.setDescriptionAxe(axeForm.getDescriptionAxe());

        axeService.update(axe);
        return REDIRECT_AXE_LIST;
    }

    @GetMapping("axes/file/edit/{id}")
    String editFile(@PathVariable("id") String id, Model model, @Valid @ModelAttribute FileForm fileForm){

        Axe axe = axeService.getOne(id);

        try {
            axe.setImage(new LesFonctions().updateImage(imageUserService.getOne(axe.getImageUser().getCodeImageUser()), imageUserService, fileForm.getFile()));
            axeService.update(axe);
            return REDIRECT_AXE_LIST;
        } catch (IOException e){
            e.printStackTrace();
            model.addAttribute("id", id);
            return AXE_EDIT_FILE_VIEW_NAME;
        }
    }

    @GetMapping("axes/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!axeService.isExiste(id)){ajaxResponseBody.setMsg("axe inexistant!");}
        else {
            axeService.delete(id);
            //imageUserService.delete(axeService.getOne(id).getImageUser().getCodeImageUser());
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
