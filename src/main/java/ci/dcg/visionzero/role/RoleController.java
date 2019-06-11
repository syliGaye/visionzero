package ci.dcg.visionzero.role;

import ci.dcg.visionzero.files.FileStorageService;
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
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private FileStorageService fileStorageService;

    @ModelAttribute("titrepage")
    String titre() {
        return ROLE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return ROLE_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return ROLE_SOUS_MODULE_UN;
    }

    @GetMapping("roles")
    String roles(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        model.addAttribute("listRole", roleService.findAll());
        return ROLE_LIST_VIEW_NAME;
    }

    @GetMapping("roles/add")
    String add(@PathVariable(value = "id", required = false) String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        model.addAttribute(new RoleForm());

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return ROLE_ADD_VIEW_NAME.concat(" :: roleForm");
        }
        return ROLE_ADD_VIEW_NAME;
    }

    @PostMapping("roles/add")
    String add(Model model, @Valid @ModelAttribute RoleForm roleForm, Errors errors){
        roleValidator.validate(roleForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return ROLE_ADD_VIEW_NAME;
        }

        roleService.save(roleForm.createNewRole());
        return REDIRECT_ROLE_LIST;
    }

    @GetMapping("roles/edit/{id}")
    String edit(@PathVariable("id") String id, Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        Role role = roleService.getOne(id);
        model.addAttribute(new RoleForm(role.getId(), role.getRoleName()));

        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return ROLE_EDIT_VIEW_NAME.concat(" :: roleForm");
        }
        return ROLE_EDIT_VIEW_NAME;
    }

    @PostMapping("roles/edit")
    String edit(Model model, @Valid @ModelAttribute RoleForm roleForm, Errors errors){
        roleValidator.validate(roleForm, errors);

        if (errors.hasErrors()){
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return ROLE_EDIT_VIEW_NAME;
        }

        roleService.update(roleForm.updateRole());
        return REDIRECT_ROLE_LIST;
    }

    @GetMapping("roles/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!roleService.isExiste(id)){ajaxResponseBody.setMsg("role inexistant!");}
        else {
            roleService.delete(id);
            ajaxResponseBody.setMsg("ok");
        }
        return ResponseEntity.ok(ajaxResponseBody);
    }
}
