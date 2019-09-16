package ci.dcg.visionzero.entreprise;

import ci.dcg.visionzero.axe.Axe;
import ci.dcg.visionzero.axe.AxeForLineChart;
import ci.dcg.visionzero.axe.AxeForPieChart;
import ci.dcg.visionzero.axe.AxeService;
import ci.dcg.visionzero.continent.ContinentService;
import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.files.FileStorageService;
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

    @Autowired
    private CouleurService couleurService;

    @Autowired
    private AxeService axeService;

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
                else ajaxResponseBody.setResult(entrepriseService.findAllByPays(pays));
            }
        }
        else {
            if (!secteuractivite.equals("null")){
                if (!raisonsociale.equals("null")){
                    ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActiviteAndRaisonSociale(
                            secteuractivite,
                            raisonsociale));
                }
                else ajaxResponseBody.setResult(entrepriseService.findAllBySecteurActivite(secteuractivite));
            }
            else {
                if (!raisonsociale.equals("null"))ajaxResponseBody.setResult(entrepriseService.findAllByRaisonSociale(raisonsociale));
                else ajaxResponseBody.setResult(entrepriseService.findAll());
            }
        }

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/pays/{continent}")
    ResponseEntity<?> getAllPays(@PathVariable("continent") String continent){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        if (!continent.equals("null")) ajaxResponseBody.setResult(paysService.findAllByContinent(continent));
        else ajaxResponseBody.setResult(paysService.findAll());

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/pays/entreprise/{pays}")
    ResponseEntity<?> getAllEntreprisesByContinent(@PathVariable("pays") String pays){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();

        ajaxResponseBody.setResult(entrepriseService.findAllByPays(pays));

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/bar/{entreprise}/{pays}/{secteuractivite}/{raisonsociale}")
    ResponseEntity<?> getValuesForBarCharts(@PathVariable("entreprise") String codeEntreprise,
                                         @PathVariable("pays") String codePays,
                                         @PathVariable("secteuractivite") String codeSecteurActivite,
                                         @PathVariable("raisonsociale") String codeRaisonSociale){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<EntrepriseOneList> entrepriseOneLists = new ArrayList<>();

        if (codeEntreprise.equals("null")){
            List<Entreprise> entreprises = new LesFonctions().getEnterprisesList(codePays, codeSecteurActivite, codeRaisonSociale, entrepriseService);

            List<Couleur> couleurs = couleurService.findAll();
            int index = 0;

            for (Entreprise entr:entreprises){
                EntrepriseOneList entrepriseOneList = new EntrepriseOneList();
                entrepriseOneList.setCouleur(couleurs.get(index));
                entrepriseOneList.setNomEntreprise(entr.getNomEntreprise());
                entrepriseOneList.setAxeOneLists(new LesFonctions().getAxesList(notationAxeService.findAllByEntreprise(entr.getCodeEntreprise())));
                entrepriseOneLists.add(entrepriseOneList);
                index++;
            }

        }
        else {
            EntrepriseOneList entrepriseOneList = new EntrepriseOneList();
            entrepriseOneList.setNomEntreprise(entrepriseService.getOne(codeEntreprise).getNomEntreprise());
            entrepriseOneList.setAxeOneLists(new LesFonctions().getAxesList(notationAxeService.findAllByEntreprise(codeEntreprise)));
            entrepriseOneLists.add(entrepriseOneList);
        }

        ajaxResponseBody.setResult(entrepriseOneLists);

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/line/{entreprise}/{pays}/{secteuractivite}/{raisonsociale}")
    ResponseEntity<?> getValuesForLineCharts(@PathVariable("entreprise") String codeEntreprise,
                                         @PathVariable("pays") String codePays,
                                         @PathVariable("secteuractivite") String codeSecteurActivite,
                                         @PathVariable("raisonsociale") String codeRaisonSociale){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<AxeForLineChart> axeForLineCharts = new ArrayList<>();

        if (codeEntreprise.equals("null")){
            List<Entreprise> entreprises = new LesFonctions().getEnterprisesList(codePays, codeSecteurActivite, codeRaisonSociale, entrepriseService);

            List<Couleur> couleurs = couleurService.findAll();
            int index = 0;

            for (Axe axe:axeService.findAll()) {
                AxeForLineChart axeForLineChart = new AxeForLineChart();
                List<EntrepriseForLineChart> entrepriseForLineCharts = new ArrayList<>();

                for (Entreprise entr:entreprises){
                    EntrepriseForLineChart entrepriseForLineChart = new EntrepriseForLineChart();
                    entrepriseForLineChart.setCouleur(couleurs.get(index).getHexCouleur());
                    entrepriseForLineChart.setNomEntreprise(entr.getNomEntreprise());
                    entrepriseForLineChart.setNotationAxe(notationAxeService.findByAxeAndEntreprise(axe.getCodeAxe(), entr.getCodeEntreprise()).getValeurNotationAxe());
                    entrepriseForLineCharts.add(entrepriseForLineChart);
                    index++;
                }

                axeForLineChart.setLibelleAxe(axe.getLibelleAxe());
                axeForLineChart.setEntrepriseForLineCharts(entrepriseForLineCharts);
                axeForLineCharts.add(axeForLineChart);
            }
        }
        else {

            for (Axe axe:axeService.findAll()) {
                AxeForLineChart axeForLineChart = new AxeForLineChart();
                List<EntrepriseForLineChart> entrepriseForLineCharts = new ArrayList<>();
                EntrepriseForLineChart entrepriseForLineChart = new EntrepriseForLineChart();

                entrepriseForLineChart.setCouleur(couleurService.findAll().get(0).getHexCouleur());
                entrepriseForLineChart.setNomEntreprise(entrepriseService.getOne(codeEntreprise).getNomEntreprise());
                entrepriseForLineChart.setNotationAxe(notationAxeService.findByAxeAndEntreprise(axe.getCodeAxe(), entrepriseService.getOne(codeEntreprise).getCodeEntreprise()).getValeurNotationAxe());
                entrepriseForLineCharts.add(entrepriseForLineChart);

                axeForLineChart.setLibelleAxe(axe.getLibelleAxe());
                axeForLineChart.setEntrepriseForLineCharts(entrepriseForLineCharts);
                axeForLineCharts.add(axeForLineChart);
            }
        }

        ajaxResponseBody.setResult(axeForLineCharts);

        return ResponseEntity.ok(ajaxResponseBody);
    }

    @GetMapping("statistiques/pie/{entreprise}/{pays}/{secteuractivite}/{raisonsociale}")
    ResponseEntity<?> getValuesForPieCharts(@PathVariable("entreprise") String codeEntreprise,
                                             @PathVariable("pays") String codePays,
                                             @PathVariable("secteuractivite") String codeSecteurActivite,
                                             @PathVariable("raisonsociale") String codeRaisonSociale){
        AjaxResponseBody ajaxResponseBody = new AjaxResponseBody();
        List<AxeForPieChart> axeForPieCharts = new ArrayList<>();

        if (codeEntreprise.equals("null")){
            List<Entreprise> entreprises = new LesFonctions().getEnterprisesList(codePays, codeSecteurActivite, codeRaisonSociale, entrepriseService);

            for (Axe axe:axeService.findAll()){
                AxeForPieChart axeForPieChart = new AxeForPieChart();
                double valeurAxe = 0.0;

                for (Entreprise entr:entreprises){
                    valeurAxe += notationAxeService.findByAxeAndEntreprise(axe.getCodeAxe(), entr.getCodeEntreprise()).getValeurNotationAxe();
                }

                axeForPieChart.setLibelleAxe(axe.getLibelleAxe());
                axeForPieChart.setHexCouleur(axe.getCouleur().getHexCouleur());
                axeForPieChart.setValeurNote(valeurAxe);
                axeForPieCharts.add(axeForPieChart);
            }

        }
        else {
            for (Axe axe:axeService.findAll()){
                AxeForPieChart axeForPieChart = new AxeForPieChart();

                axeForPieChart.setLibelleAxe(axe.getLibelleAxe());
                axeForPieChart.setHexCouleur(axe.getCouleur().getHexCouleur());
                axeForPieChart.setValeurNote(notationAxeService.findByAxeAndEntreprise(axe.getCodeAxe(), entrepriseService.getOne(codeEntreprise).getCodeEntreprise()).getValeurNotationAxe());
                axeForPieCharts.add(axeForPieChart);
            }

        }

        ajaxResponseBody.setResult(axeForPieCharts);

        return ResponseEntity.ok(ajaxResponseBody);
    }

}
