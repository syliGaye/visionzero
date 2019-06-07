package ci.dcg.visionzero.role;

import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utilisateur utilisateur = userService.findByLogin(user.getUsername());

        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("userConnected", new Utilisateur(user.getUsername(), utilisateur.getEmail(), utilisateur.getImageUser()));
        return ROLE_LIST_VIEW_NAME;
    }



    @DeleteMapping("roles/delete/{id}")
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
