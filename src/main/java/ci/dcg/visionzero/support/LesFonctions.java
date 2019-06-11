package ci.dcg.visionzero.support;

import ci.dcg.visionzero.couleur.Couleur;
import ci.dcg.visionzero.couleur.CouleurService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class LesFonctions {

    @Autowired
    private CouleurService couleurService;

    public LesFonctions() {
    }

    public double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double round_2(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public Couleur getValueColor(double value){
        //Couleur couleur = new Couleur();
        Couleur couleur;

        double poucentage = (value * 100)/3;
        double val1 = 100/3;
        double val2 = (100/3)*2;

        if (poucentage <= val1){couleur = couleurService.findCouleurByLibelleCouleur("red");}
        else if ((poucentage > val1) && (poucentage <= val2)){couleur = couleurService.findCouleurByLibelleCouleur("yellow");}
        else if ((poucentage > val2) && (poucentage <= 100)){couleur = couleurService.findCouleurByLibelleCouleur("green");}
        else {couleur = couleurService.findCouleurByLibelleCouleur("black");}

        return couleur;
    }

    public String createView(Optional<String> viewResolver, String viewName) {
        String result = viewResolver.isPresent() ? viewResolver.get() : "jsp";
        result += viewName;
        return result;
    }

    public void profileDeConnexion(Model model, FileStorageService fileStorageService, UserService userService){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Utilisateur utilisateur = userService.findByLogin(user.getUsername());
        fileStorageService.storeFileUser(utilisateur.getImageUser());

        model.addAttribute("userConnected", new Utilisateur(user.getUsername(), utilisateur.getEmail(), utilisateur.getImageUser()));
    }
}
