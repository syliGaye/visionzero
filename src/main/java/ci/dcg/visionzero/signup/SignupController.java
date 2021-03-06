package ci.dcg.visionzero.signup;

import ci.dcg.visionzero.email.EmailService;
import ci.dcg.visionzero.files.FileStorageService;
import ci.dcg.visionzero.imageuser.ImageUserService;
import ci.dcg.visionzero.role.RoleService;
import ci.dcg.visionzero.support.LesFonctions;
import ci.dcg.visionzero.utilisateur.UserService;
import ci.dcg.visionzero.utilisateur.Utilisateur;
import ci.dcg.visionzero.utilisateur.UtilisateurDetailService;
import ci.dcg.visionzero.web.AjaxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

import static ci.dcg.visionzero.web.WebViewName.*;

@Controller
public class SignupController {

    private static Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UtilisateurDetailService utilisateurDetailService;

    @Autowired
    private SignupValidator signupValidator;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ImageUserService imageUserService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired private EmailService emailService;

    @ModelAttribute("titrepage")
    String titre() {
        return SIGNUP_PAGE_TITLE;
    }

    @GetMapping("signup")
    String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith){

        if (userService.count() != 0) return SIGNIN_VIEW_NAME;

        model.addAttribute(new SignupForm());
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        return SIGNUP_VIEW_NAME;
    }

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {

		/*Map<String, String> map = new HashMap<String, String>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}*/

        try {
            signupValidator.validate(signupForm, errors);

            if (errors.hasErrors()) {
                return SIGNUP_VIEW_NAME;
            }

            Utilisateur utilisateur = signupForm.createSuperAdmin();
            String codeUtilisateur = userService.retourneId();

            utilisateur.setRole(roleService.findByRoleName("ROLE_SUPERADMIN"));
            utilisateur.setId(codeUtilisateur);
            utilisateur.setImageUser(new LesFonctions().createImageForUser(codeUtilisateur, imageUserService, signupForm.getFile()));

            Utilisateur utilisateurSaved = userService.save(utilisateur);
            utilisateurDetailService.loginUser(utilisateurSaved);

            return REDIRECT_SIGNIN;
        } catch (IOException e) {
            e.printStackTrace();
            return REDIRECT_SIGNUP;
        }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Impossible de déterminer le type de fichier.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
