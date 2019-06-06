package ci.dcg.visionzero.home;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.role.Role;
import ci.dcg.visionzero.role.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static ci.dcg.visionzero.web.WebViewName.HOME_VIEW_NAME;
import static ci.dcg.visionzero.web.WebViewName.REDIRECT_SIGNIN;

@Controller
class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @ModelAttribute("titrepage")
    String titre() {
        return "Vision Zero | Accueil";
    }

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/home")
    String index(Principal principal) {
        try{
            User user = (User) principal;

            if (user != null) logger.info(user.toString());

            return HOME_VIEW_NAME;
        }catch (ClassCastException e) {
            return REDIRECT_SIGNIN;
        }
    }
}
