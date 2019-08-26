package ci.dcg.visionzero.axe;

import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.entreprise.EntrepriseService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class RegleorController {
    private static Logger logger = LoggerFactory.getLogger(RegleorController.class);

    @Autowired
    private AxeService axeService;

    @Autowired
    private CouleurService couleurService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ImageUserService imageUserService;

    @Autowired
    private EntrepriseService entrepriseService;

    @ModelAttribute("titrepage")
    String titre() {
        return REGLE_OR_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return REGLE_OR_MODULE;
    }

    @GetMapping("regles-or")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        List<Axe> axeList = axeService.findAll();

        if (!axeList.isEmpty()){
            for (Axe axe:axeList) {
                fileStorageService.storeFileUser(axe.getImageUser());
            }
        }

        model.addAttribute("lesAxes", axeList);
        return REGLE_OR_LIST_VIEW_NAME;
    }


}
