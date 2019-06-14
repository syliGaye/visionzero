package ci.dcg.visionzero.web;

public class WebViewName {

    /** ETAT **/
    public static final String DO_INSERT = "insertion";
    public static final String DO_UPDATE = "modification";
    public static final String DO_DELETE = "suppression";

    /** Home Controller **/
    public static final String HOME_VIEW_NAME = "home/home";
    public static final String REDIRECT_HOME = "redirect:/home";
    public static final String HOME_MODULE = "home";
    public static final String HOME_PAGE_TITLE = "Vision Zero | Accueil";

    /** Signup Controller **/
    public static final String SIGNUP_VIEW_NAME = "signup/registration";
    public static final String UPLOADFILE_VIEW_NAME = "signup/upload";
    public static final String REDIRECT_SIGNUP = "redirect:/signup";
    public static final String REDIRECT_UPLOADFILE = "redirect:/upload";
    public static final String SIGNUP_PAGE_TITLE = "Vision Zero | Register";

    /** Signin Controller **/
    public static final String SIGNIN_VIEW_NAME = "signin/login";
    public static final String REDIRECT_SIGNIN = "redirect:/";
    public static final String SIGNIN_PAGE_TITLE = "Vision Zero | Connexion";

    /** Signout Controller **/
    public static final String REDIRECT_SIGNOUT = "redirect:/logout";

    /** Role Controller **/
    public static final String ROLE_LIST_VIEW_NAME = "parametres/role/roles";
    public static final String ROLE_ADD_VIEW_NAME = "parametres/role/add";
    public static final String ROLE_EDIT_VIEW_NAME = "parametres/role/edit";
    public static final String REDIRECT_ROLE_LIST = "redirect:/roles";
    public static final String REDIRECT_ROLE_ADD = "redirect:/roles/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String ROLE_MODULE = "administration";
    public static final String ROLE_SOUS_MODULE_UN = "roles";
    public static final String ROLE_TITLE = "Vision Zero | Profiles Utilisateur";

    /** Utilisateur Controller **/
    public static final String USER_LIST_VIEW_NAME = "parametres/user/users";
    public static final String USER_ADD_VIEW_NAME = "parametres/user/add";
    public static final String USER_EDIT_VIEW_NAME = "parametres/user/edit";
    public static final String REDIRECT_USER_LIST = "redirect:/users";
    public static final String REDIRECT_USER_ADD = "redirect:/users/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String USER_MODULE = "administration";
    public static final String USER_SOUS_MODULE_UN = "utilisateurs";
    public static final String USER_TITLE = "Vision Zero | Gestion des Utilisateur";

    /** Continent Controller **/
    public static final String CONTINENT_LIST_VIEW_NAME = "parametres/continent/continents";
    public static final String CONTINENT_ADD_VIEW_NAME = "parametres/continent/add";
    public static final String CONTINENT_EDIT_VIEW_NAME = "parametres/continent/edit";
    public static final String REDIRECT_CONTINENT_LIST = "redirect:/continents";
    public static final String REDIRECT_CONTINENT_ADD = "redirect:/continents/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String CONTINENT_MODULE = "parametrages";
    public static final String CONTINENT_SOUS_MODULE_UN = "parametrespecifiques";
    public static final String CONTINENT_SOUS_MODULE_DEUX = "continents";
    public static final String CONTINENT_TITLE = "Vision Zero | Gestion des Continents";

    /** Pays Controller **/
    public static final String PAYS_LIST_VIEW_NAME = "parametres/pays/pays";
    public static final String PAYS_ADD_VIEW_NAME = "parametres/pays/add";
    public static final String PAYS_EDIT_VIEW_NAME = "parametres/pays/edit";
    public static final String REDIRECT_PAYS_LIST = "redirect:/pays";
    public static final String REDIRECT_PAYS_ADD = "redirect:/pays/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String PAYS_MODULE = "parametrages";
    public static final String PAYS_SOUS_MODULE_UN = "parametrespecifiques";
    public static final String PAYS_SOUS_MODULE_DEUX = "pays";
    public static final String PAYS_TITLE = "Vision Zero | Gestion des Pays";

    /** Secteur Activite Controller **/
    public static final String SECTEUR_ACTIVITE_LIST_VIEW_NAME = "parametres/secteuractivite/secteuractivites";
    public static final String SECTEUR_ACTIVITE_ADD_VIEW_NAME = "parametres/secteuractivite/add";
    public static final String SECTEUR_ACTIVITE_EDIT_VIEW_NAME = "parametres/secteuractivite/edit";
    public static final String REDIRECT_SECTEUR_ACTIVITE_LIST = "redirect:/secteuractivites";
    public static final String REDIRECT_SECTEUR_ACTIVITE_ADD = "redirect:/secteuractivites/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String SECTEUR_ACTIVITE_MODULE = "parametrages";
    public static final String SECTEUR_ACTIVITE_SOUS_MODULE_UN = "parametrespecifiques";
    public static final String SECTEUR_ACTIVITE_SOUS_MODULE_DEUX = "secteursactivites";
    public static final String SECTEUR_ACTIVITE_TITLE = "Vision Zero | Gestion des Secteurs d'Activit&eacute;";

    /** Raison Sociale Controller **/
    public static final String RAISON_SOCIALE_LIST_VIEW_NAME = "parametres/raisonsociale/raisonsociales";
    public static final String RAISON_SOCIALE_ADD_VIEW_NAME = "parametres/raisonsociale/add";
    public static final String RAISON_SOCIALE_EDIT_VIEW_NAME = "parametres/raisonsociale/edit";
    public static final String RAISON_SOCIALE_ACTIVITE_LIST = "redirect:/raisonsociales";
    public static final String RAISON_SOCIALE_ACTIVITE_ADD = "redirect:/raisonsociales/add";
    //public static final String REDIRECT_RAISON_SOCIALE_EDIT = "redirect:/roles/add";
    public static final String RAISON_SOCIALE_MODULE = "parametrages";
    public static final String RAISON_SOCIALE_SOUS_MODULE_UN = "parametrespecifiques";
    public static final String RAISON_SOCIALE_SOUS_MODULE_DEUX = "raisonsociales";
    public static final String RAISON_SOCIALE_TITLE = "Vision Zero | Gestion des Raisons Sociales";

    /** Evaluation Controller **/
    public static final String DOMAINE_LIST_VIEW_NAME = "parametres/domaine/domaines";
    public static final String DOMAINE_ADD_VIEW_NAME = "parametres/domaine/add";
    public static final String DOMAINE_EDIT_VIEW_NAME = "parametres/domaine/edit";
    public static final String REDIRECT_DOMAINE_LIST = "redirect:/domaines";
    public static final String REDIRECT_DOMAINE_ADD = "redirect:/domaines/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String DOMAINE_MODULE = "parametrages";
    public static final String DOMAINE_SOUS_MODULE_UN = "parametresgeneraux";
    public static final String DOMAINE_SOUS_MODULE_DEUX = "domaines";
    public static final String DOMAINE_TITLE = "Vision Zero | Gestion des Domaines";

    /** Question Controller **/
    public static final String QUESTION_LIST_VIEW_NAME = "parametres/question/questions";
    public static final String QUESTION_ADD_VIEW_NAME = "parametres/question/add";
    public static final String QUESTION_EDIT_VIEW_NAME = "parametres/question/edit";
    public static final String REDIRECT_QUESTION_LIST = "redirect:/questions";
    public static final String REDIRECT_QUESTION_ADD = "redirect:/questions/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String QUESTION_MODULE = "parametrages";
    public static final String QUESTION_SOUS_MODULE_UN = "parametresgeneraux";
    public static final String QUESTION_SOUS_MODULE_DEUX = "questions";
    public static final String QUESTION_TITLE = "Vision Zero | Gestion des Questionnaires";

    /** Reponse Controller **/
    public static final String REPONSE_LIST_VIEW_NAME = "parametres/reponse/reponses";
    public static final String REPONSE_ADD_VIEW_NAME = "parametres/reponse/add";
    public static final String REPONSE_EDIT_VIEW_NAME = "parametres/reponse/edit";
    public static final String REDIRECT_REPONSE_LIST = "redirect:/reponses";
    public static final String REDIRECT_REPONSE_ADD = "redirect:/reponses/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String REPONSE_MODULE = "parametrages";
    public static final String REPONSE_SOUS_MODULE_UN = "parametresgeneraux";
    public static final String REPONSE_SOUS_MODULE_DEUX = "reponses";
    public static final String REPONSE_TITLE = "Vision Zero | Gestion des Reponses";

    /** Entreprrise Controller **/
    public static final String ENTREPRISE_LIST_VIEW_NAME = "admin/entreprise/entreprises";
    public static final String ENTREPRISE_ADD_VIEW_NAME = "admin/entreprise/add";
    public static final String ENTREPRISE_EDIT_VIEW_NAME = "admin/entreprise/edit";
    public static final String REDIRECT_ENTREPRISE_LIST = "redirect:/entreprises";
    public static final String REDIRECT_ENTREPRISE_ADD = "redirect:/entreprises/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String ENTREPRISE_MODULE = "entreprises";
    public static final String ENTREPRISE_TITLE = "Vision Zero | Gestion des Entreprises";

    /** Axe Controller **/
    public static final String AXE_LIST_VIEW_NAME = "admin/reglesdor/regledors";
    //public static final String ENTREPRISE_ADD_VIEW_NAME = "admin/entreprise/add";
    //public static final String ENTREPRISE_EDIT_VIEW_NAME = "admin/entreprise/edit";
    //public static final String REDIRECT_ENTREPRISE_LIST = "redirect:/entreprises";
    //public static final String REDIRECT_ENTREPRISE_ADD = "redirect:/entreprises/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String AXE_MODULE = "reglesor";
    public static final String AXE_TITLE = "Vision Zero | Les R&egrave;gles d'Or";

    /** Statistique Controller **/
    public static final String STATISTIQUE_ALL_VIEW_NAME = "admin/statistique/statistiques";
    //public static final String ENTREPRISE_ADD_VIEW_NAME = "admin/entreprise/add";
    //public static final String ENTREPRISE_EDIT_VIEW_NAME = "admin/entreprise/edit";
    //public static final String REDIRECT_ENTREPRISE_LIST = "redirect:/entreprises";
    //public static final String REDIRECT_ENTREPRISE_ADD = "redirect:/entreprises/add";
    //public static final String REDIRECT_ROLE_EDIT = "redirect:/roles/add";
    public static final String STATISTIQUE_MODULE = "statistiques";
    //public static final String ENTREPRISE_TITLE = "Vision Zero | Gestion des Entreprises";

}
