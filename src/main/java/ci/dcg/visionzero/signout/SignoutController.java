package ci.dcg.visionzero.signout;

import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ci.dcg.visionzero.web.WebViewName.REDIRECT_HOME;
import static ci.dcg.visionzero.web.WebViewName.REDIRECT_SIGNOUT;

@Controller
public class SignoutController {

    private static Logger logger = LoggerFactory.getLogger(SignoutController.class);

    @Autowired
    private UserService userService;

    /*@GetMapping("logout")
    String signout(){
        return REDIRECT_SIGNIN;
    }*/

    @PostMapping("gotologout")
    String beforeSignout(Utilisateur utilisateur){
        Utilisateur utilisateur1 = userService.findByLogin(utilisateur.getLogin());
        utilisateur1.setActive(0);
        userService.update(utilisateur1);
        return REDIRECT_SIGNOUT;
    }
}
