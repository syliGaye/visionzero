package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.axe.AxeServiceImpl;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.isoalpha.*;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.UtilisateurController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class PaysController {
    private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private IsoAlphaTroisService alphaTroisService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private IsoAlphaDeuxService alphaDeuxService;

    @Autowired
    private PaysService paysService;

    @ModelAttribute("titrepage")
    String titre() {
        return PAYS_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return PAYS_MODULE;
    }

    @ModelAttribute("sousmoduleun")
    String sousmoduleun() {
        return PAYS_SOUS_MODULE_UN;
    }

    @ModelAttribute("sousmoduledeux")
    String sousmoduledeux() {
        return PAYS_SOUS_MODULE_DEUX;
    }

    @GetMapping("pays")
    String pays(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);
        List<Pays> paysList = paysService.findAll();
        List<Map> mapList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (Pays pays : paysList) {
            map.clear();
            IsoAlphaDeux alphaDeux = alphaDeuxService.findByPays(pays.getIdPays());
            IsoAlphaTrois alphaTrois = alphaTroisService.findByPays(pays.getIdPays());

            map.put("id", pays.getIdPays());   map.put("pays", pays.getLibellePays());   map.put("continent", pays.getContinent().getLibelleContinent());
            map.put("alphadeux", alphaDeux.getCodeIsoAlphaDeux());   map.put("alphatrois", alphaTrois.getCodeIsoAlphaTrois());
            mapList.add(map);
        }

        model.addAttribute("listPays", mapList);
        return PAYS_LIST_VIEW_NAME;
    }
}
