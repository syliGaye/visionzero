package ci.dcg.visionzero.signin;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.role.Role;
import ci.dcg.visionzero.role.RoleService;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class SigninController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CouleurService couleurService;

    @Autowired
    private FileStorageService fileStorageService;

    @ModelAttribute("titrepage")
    String titre() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return HOME_PAGE_TITLE;
        }
        return SIGNIN_PAGE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return HOME_MODULE;
    }

    @GetMapping("/")
    String login(Model model){
        if(userService.count() == 0){
            if(roleService.count() == 0){
                Role role_superAdmin = new Role("ROLE_SUPERADMIN");
                roleService.save(role_superAdmin);
            }

            if (couleurService.count() == 0){
                // Create all Colors
                Couleur col_red = new Couleur("red", "rgb(244, 67, 54)");
                Couleur col_pink = new Couleur("pink", "rgb(233, 30, 99)");
                Couleur col_purple = new Couleur("purple","rgb(156, 39, 176)");
                Couleur col_deepPurple = new Couleur("deep-purple", "rgb(103, 58, 183)");
                Couleur col_indigo = new Couleur("indigo", "rgb(63, 81, 181)");
                Couleur col_blue = new Couleur("blue", "rgb(33, 150, 243)");
                Couleur col_lightBlue = new Couleur("light-blue","rgb(3, 169, 244)");
                Couleur col_cyan = new Couleur("cyan","rgb(0, 188, 212)");
                Couleur col_teal = new Couleur("teal", "rgb(0, 150, 136)");
                Couleur col_green = new Couleur("green", "rgb(76, 175, 80)");
                Couleur col_lightGreen = new Couleur("light-green", "rgb(139, 195, 74)");
                Couleur col_lime = new Couleur("lime", "rgb(205, 220, 57)");
                Couleur col_yellow = new Couleur("yellow", "rgb(255, 235, 59)");
                Couleur col_amber = new Couleur("amber", "rgb(255, 193, 7)");
                Couleur col_orange = new Couleur("orange", "rgb(255, 152, 0)");
                Couleur col_deepOrange = new Couleur("deep-orange", "rgb(255, 87, 34)");
                Couleur col_brown = new Couleur("brown", "rgb(121, 85, 72)");
                Couleur col_grey = new Couleur("grey", "rgb(158, 158, 158)");
                Couleur col_blueGrey = new Couleur("blue-grey", "rgb(96, 125, 139)");
                Couleur col_black = new Couleur("black", "rgb(0, 0, 0)");

                couleurService.save(col_amber);
                couleurService.save(col_black);
                couleurService.save(col_blue);
                couleurService.save(col_blueGrey);
                couleurService.save(col_brown);
                couleurService.save(col_cyan);
                couleurService.save(col_deepOrange);
                couleurService.save(col_deepPurple);
                couleurService.save(col_green);
                couleurService.save(col_grey);
                couleurService.save(col_indigo);
                couleurService.save(col_lightBlue);
                couleurService.save(col_lightGreen);
                couleurService.save(col_lime);
                couleurService.save(col_orange);
                couleurService.save(col_pink);
                couleurService.save(col_red);
                couleurService.save(col_purple);
                couleurService.save(col_teal);
                couleurService.save(col_yellow);
            }
            return REDIRECT_SIGNUP;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Utilisateur utilisateur = userService.findByLogin(user.getUsername());
            fileStorageService.storeFileUser(utilisateur.getImageUser());

            model.addAttribute("userConnected", new Utilisateur(user.getUsername(), utilisateur.getEmail(), utilisateur.getImageUser()));
            return HOME_VIEW_NAME;
        }

        return SIGNIN_VIEW_NAME;
    }
}
