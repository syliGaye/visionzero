package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUser;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.role.Role;
import ci.dcg.visionzero.role.RoleService;
import ci.dcg.visionzero.signup.SignupValidator;
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

import javax.validation.Valid;

import java.io.IOException;

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
    private ImageUserService imageUserService;

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
    String add(Model model, @Valid @ModelAttribute UtilisateurForm utilisateurForm, Errors errors){
        utilisateurForm.setPassword("admin");   utilisateurForm.setPasswordConfirm("admin");
        utilisateurForm.setEtat(DO_INSERT);

        userValidator.validate(utilisateurForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listRole", roleService.findAll());
            return USER_ADD_VIEW_NAME;
        }

        Role role = roleService.getOne(utilisateurForm.getIdRole());
        String idUser = userService.retourneId();

        try {
            ImageUser imageUser = new LesFonctions().createImageForUser(idUser, imageUserService, utilisateurForm.getFile());
            utilisateurForm.setId(idUser);   utilisateurForm.setImageUser(imageUser);   utilisateurForm.setRole(role);

            userService.save(utilisateurForm.createNewUser());
            return REDIRECT_USER_LIST;
        } catch (IOException e) {
            e.printStackTrace();
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            model.addAttribute("listRole", roleService.findAll());
            return USER_ADD_VIEW_NAME;
        }
    }

    @GetMapping("users/get/{id}")
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

    @GetMapping("users/edit/{id}")
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

    @GetMapping("users/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!userService.isExiste(id)){ajaxResponseBody.setMsg("utilisateur inexistant!");}
        else {
            userService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
