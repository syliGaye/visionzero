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
}
