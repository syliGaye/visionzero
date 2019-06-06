package ci.dcg.visionzero.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @ModelAttribute("titrepage")
    String titre() {
        return HOME_PAGE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return HOME_MODULE;
    }

    @GetMapping("home")
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
