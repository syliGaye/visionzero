package ci.dcg.visionzero.signin;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.email.EmailService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.role.Role;
import ci.dcg.visionzero.role.RoleService;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired private EmailService emailService;

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
                Couleur col_red = new Couleur("red", "rgb(255,74,67)", "#FF4A43");
                Couleur col_pink = new Couleur("pink", "rgb(233, 30, 99)", "#E91E63");
                Couleur col_purple = new Couleur("purple","rgb(156, 39, 176)", "#9C27B0");
                Couleur col_deepPurple = new Couleur("deep-purple", "rgb(103, 58, 183)", "#673AB7");
                Couleur col_indigo = new Couleur("indigo", "rgb(63, 81, 181)", "#3F51B5");
                Couleur col_blue = new Couleur("blue", "rgb(65,139,202)", "#418BCA");
                Couleur col_lightBlue = new Couleur("light-blue","rgb(3, 169, 244)", "#03A9F4");
                Couleur col_cyan = new Couleur("cyan","rgb(34,190,239)", "#22BEEF");
                Couleur col_teal = new Couleur("teal", "rgb(0, 150, 136)", "#009688");
                Couleur col_green = new Couleur("green", "rgb(162,210,0)", "#A2D200");
                Couleur col_lightGreen = new Couleur("light-green", "rgb(139, 195, 74)", "#8BC34A");
                Couleur col_lime = new Couleur("lime", "rgb(205, 220, 57)", "#CDDC39");
                Couleur col_yellow = new Couleur("yellow", "rgb(255, 235, 59)", "#FFEB3B");
                Couleur col_amber = new Couleur("amber", "rgb(255, 193, 7)", "#FFC107");
                Couleur col_orange = new Couleur("orange", "rgb(255,193,0)", "#FFC100");
                Couleur col_deepOrange = new Couleur("deep-orange", "rgb(255, 87, 34)", "#FF5722");
                Couleur col_brown = new Couleur("brown", "rgb(121, 85, 72)", "#795548");
                Couleur col_grey = new Couleur("grey", "rgb(158, 158, 158)", "#9E9E9E");
                Couleur col_blueGrey = new Couleur("blue-grey", "rgb(96, 125, 139)", "#607D8B");
                Couleur col_black = new Couleur("black", "rgb(0, 0, 0)", "#000000");
                Couleur col_primary = new Couleur("primary", "rgb(51, 122, 183)", "#337AB7");
                Couleur col_drank = new Couleur("drank", "rgb(164,7,120)", "#A40778");
                Couleur col_lightred = new Couleur("lightred", "rgb(224,93,111)", "#E05D6F");
                Couleur col_dutch = new Couleur("dutch", "rgb(22,147,165)", "#1693A5");
                Couleur col_amethyst = new Couleur("amethyst", "rgb(205,151,235)", "#CD97EB");
                Couleur col_darkgray = new Couleur("darkgray", "rgb(51,51,51)", "#333333");
                Couleur col_default = new Couleur("default", "rgb(221,221,221)", "#DDDDDD");
                Couleur col_greensea = new Couleur("greensea", "rgb(22,160,133)", "#16A085");
                Couleur col_hotpink = new Couleur("hotpink", "rgb(255,0,102)", "#FF0066");
                Couleur col_slategray = new Couleur("slategray", "rgb(63,78,98)", "#3F4E62");

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
                couleurService.save(col_primary);
                couleurService.save(col_drank);
                couleurService.save(col_dutch);
                couleurService.save(col_lightred);
                couleurService.save(col_amethyst);
                couleurService.save(col_darkgray);
                couleurService.save(col_default);
                couleurService.save(col_greensea);
                couleurService.save(col_hotpink);
                couleurService.save(col_slategray);
            }
            return REDIRECT_SIGNUP;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
            emailService.prepareAndSend("sylvestregaye@gmail.com", "GAYE Mehibo", "TEST");

            return HOME_VIEW_NAME;
        }

        return SIGNIN_VIEW_NAME;
    }
}
