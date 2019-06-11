package ci.dcg.visionzero.web;

public class WebViewName {

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

}
