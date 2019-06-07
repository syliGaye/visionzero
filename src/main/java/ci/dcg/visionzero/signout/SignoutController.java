package ci.dcg.visionzero.signout;

import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ci.dcg.visionzero.web.WebViewName.REDIRECT_SIGNIN;

@Controller
public class SignoutController {

    @Autowired
    private UserService userService;

    @PostMapping("logout")
    String signout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Utilisateur utilisateur = userService.findByLogin(user.getUsername());
            utilisateur.setActive(0);
            userService.update(utilisateur);
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return REDIRECT_SIGNIN;
    }

}
