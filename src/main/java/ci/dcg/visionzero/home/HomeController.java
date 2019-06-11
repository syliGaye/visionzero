package ci.dcg.visionzero.home;

import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @ModelAttribute("titrepage")
    String titre() {
        return HOME_PAGE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return HOME_MODULE;
    }

    @GetMapping("home")
    String index(Model model) {
        try{
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

            return HOME_VIEW_NAME;
        }catch (ClassCastException e) {
            return REDIRECT_SIGNIN;
        }
    }
}
