package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.axe.AxeOneList;
import ci.dcg.visionzero.continent.ContinentService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.notationaxe.NotationAxe;
import ci.dcg.visionzero.notationaxe.NotationAxeOneList;
import ci.dcg.visionzero.notationaxe.NotationAxeService;
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

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private NotationAxeService notationAxeService;

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

    @GetMapping("statistiques/{entreprise}/{pays}/{secteuractivite}/{raisonsociale}")
    ResponseEntity<?> getValuesForCharts(@PathVariable("entreprise") String codeEntreprise,
                                         @PathVariable("pays") String codePays,
                                         @PathVariable("secteuractivite") String codeSecteurActivite,
                                         @PathVariable("raisonsociale") String codeRaisonSociale){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<Entreprise> entreprises;
        List<NotationAxe> notationAxes = new ArrayList<>();
        List<AxeOneList> axeOneLists = new ArrayList<>();

        if (codeEntreprise.equals("null")){
            if (codePays.equals("null")){
                if (codeRaisonSociale.equals("null")){
                    if (codeSecteurActivite.equals("null")) entreprises = entrepriseService.findAll();
                    else entreprises = entrepriseService.findAllBySecteurActivite(codeSecteurActivite);
                }
                else{
                    if (codeSecteurActivite.equals("null")) entreprises = entrepriseService.findAllByRaisonSociale(codeRaisonSociale);
                    else entreprises = entrepriseService.findAllBySecteurActiviteAndRaisonSociale(codeSecteurActivite, codeRaisonSociale);
                }
            }
            else{
                if (codeRaisonSociale.equals("null")){
                    if (codeSecteurActivite.equals("null")) entreprises = entrepriseService.findAllByPays(codePays);
                    else entreprises = entrepriseService.findAllBySecteurActiviteAndPays(codeSecteurActivite, codePays);
                }
                else{
                    if (codeSecteurActivite.equals("null")) entreprises = entrepriseService.findAllByRaisonSocialeAndPays(codeRaisonSociale, codePays);
                    else entreprises = entrepriseService.findAllBySecteurActiviteAndRaisonSocialeAndPays(codeSecteurActivite, codeRaisonSociale, codePays);
                }
            }

            for (Entreprise entr:entreprises){
                List<NotationAxe> notationAxeList = notationAxeService.findAllByEntreprise(entr.getCodeEntreprise());

                for (NotationAxe notAxe:notationAxeList){
                    notationAxes.add(notAxe);
                }
            }
        }
        else {
            notationAxes = notationAxeService.findAllByEntreprise(codeEntreprise);
        }

        for (NotationAxe notationAxe:notationAxes){
            NotationAxeOneList notationAxeOneList = new NotationAxeOneList();
            AxeOneList axeOneList = new AxeOneList();

            notationAxeOneList.setCodeNotationAxe(notationAxe.getCodeNotationAxe());
            notationAxeOneList.setValeurNotationAxe(notationAxe.getValeurNotationAxe());
            axeOneList.setLibelleAxe(notationAxe.getAxe().getLibelleAxe());
            axeOneList.setCouleur(notationAxe.getAxe().getCouleur());
            axeOneList.setNotationAxeOneList(notationAxeOneList);
            axeOneLists.add(axeOneList);
        }

        ajaxResponseBody.setResult(axeOneLists);

        return ResponseEntity.ok(ajaxResponseBody);
    }

    /*@GetMapping("entreprises/continents")
    ResponseEntity<?> delete(@PathVariable("id") String id){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        ajaxResponseBody.setResult(entrepriseService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }*/
}
