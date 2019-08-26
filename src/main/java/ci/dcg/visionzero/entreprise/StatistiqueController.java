package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.continent.ContinentService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.pays.PaysService;
import ci.dcg.visionzero.raisonsociale.RaisonSocialeService;
import ci.dcg.visionzero.secteuractivite.SecteurActiviteService;
import ci.dcg.visionzero.support.AjaxResponseBody;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class StatistiqueController {
    private static Logger logger = LoggerFactory.getLogger(EntrepriseController.class);

    @Autowired
    private EntrepriseService entrepriseService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private ContinentService continentService;

    @Autowired
    private PaysService paysService;

    @Autowired
    private SecteurActiviteService secteurActiviteService;

    @Autowired
    private RaisonSocialeService raisonSocialeService;

    @ModelAttribute("titrepage")
    String titre() {
        return STATISTIQUE_TITLE;
    }

    @ModelAttribute("module")
    String module() {
        return STATISTIQUE_MODULE;
    }

    @GetMapping("statistiques")
    String getAll(Model model){
        new LesFonctions().profileDeConnexion(model, fileStorageService, userService);

        return STATISTIQUE_ALL_VIEW_NAME;
    }

    @GetMapping("statistiques/continents")
    ResponseEntity<?> getAllContinents(){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setResult(continentService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/raisonsociales")
    ResponseEntity<?> getAllRaisonsociales(){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setResult(raisonSocialeService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/secteuractivites")
    ResponseEntity<?> getAllSecteuractivites(){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setResult(secteurActiviteService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/entreprises/{pays}/{raisonsociale}/{secteuractivite}")
    ResponseEntity<?> getAllEntreprises(@PathVariable("pays") String pays,
                                        @PathVariable("raisonsociale") String raisonsociale,
                                        @PathVariable("secteuractivite") String secteuractivite){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!pays.equals("null")){
            System.out.println("bien");
            if (!secteuractivite.equals("null")){
                if (!raisonsociale.equals("null")){
                    ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActiviteAndRaisonSocialeAndPays(
                            secteuractivite,
                            raisonsociale,
                            pays));
                }
                else {
                    ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActiviteAndPays(
                            secteuractivite,
                            pays));
                }
            }
            else {
                if (!raisonsociale.equals("null")){
                    ajaxResponseBody.setResult(entrepriseService.findAllByRaisonSocialeAndPays(
                            raisonsociale,
                            pays));
                }
                else {
                    ajaxResponseBody.setResult(entrepriseService.findAllByPays(pays));
                }
            }
        }
        else {
            if (!secteuractivite.equals("null")){
                if (!raisonsociale.equals("null")){
                    ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActiviteAndRaisonSociale(
                            secteuractivite,
                            raisonsociale));
                }
                else {
                    ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActivite(secteuractivite));
                }
            }
            else {
                if (!raisonsociale.equals("null")){
                    ajaxResponseBody.setResult(entrepriseService.findAllByRaisonSociale(raisonsociale));
                }
                else {
                    ajaxResponseBody.setResult(entrepriseService.findAll());
                }
            }
        }

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/pays/{continent}")
    ResponseEntity<?> getAllPays(@PathVariable("continent") String continent){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!continent.equals("null")){
            ajaxResponseBody.setResult(paysService.findAllByContinent(continent));
        }
        else {
            ajaxResponseBody.setResult(paysService.findAll());
        }

        return ResponseEntity.ok(ajaxResponseBody);
    }

    /*@GetMapping("entreprises/continents")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setResult(entrepriseService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }*/
}
