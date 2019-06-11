package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.role.RoleService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class UtilisateurController {

    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private UserValidator userValidator;

    @ModelAttribute("titrepage")
    String titre() {
        return USER_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return USER_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return USER_SOUS_MODULE_UN;
    }

    @GetMapping("users")
    String users(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listUser", userService.findAll());
        return USER_LIST_VIEW_NAME;
    }

    @GetMapping("users/add")
    String add(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute(new UtilisateurForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return USER_ADD_VIEW_NAME.concat(" :: utilisateurForm");
        }
        return USER_ADD_VIEW_NAME;
    }

    @PostMapping("users/add")
    String add(Model model, @Valid @ModelAttribute UtilisateurForm utilisateurForm, @RequestParam("imageUser") MultipartFile file, Errors errors){
        /*userValidator.validate(utilisateurForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return USER_ADD_VIEW_NAME;
        }*/

        System.out.println(file.getName());

        //roleService.save(utilisateurForm.createNewRole());
        return REDIRECT_USER_LIST;
    }

    @GetMapping("users/edit/{id}")
    String edit(@PathVariable("id") String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        Utilisateur utilisateur = userService.getOne(id);
        //model.addAttribute(new RoleForm(role.getId(), role.getRoleName()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return USER_EDIT_VIEW_NAME.concat(" :: utilisateurForm");
        }
        return USER_EDIT_VIEW_NAME;
    }

    @PostMapping("users/edit")
    String edit(Model model, @Valid @ModelAttribute UtilisateurForm utilisateurForm, Errors errors){
        userValidator.validate(utilisateurForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return USER_EDIT_VIEW_NAME;
        }

        //roleService.update(roleForm.updateRole());
        return REDIRECT_USER_LIST;
    }

    @GetMapping("users/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!userService.isExiste(id)){ajaxResponseBody.setMsg("role inexistant!");}
        else {
            userService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
