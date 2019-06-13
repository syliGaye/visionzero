package ci.dcg.visionzero.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Utilisateur.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UtilisateurForm user = (UtilisateurForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "NotEmpty");
        if (user.getLogin().length() < 6 || user.getLogin().length() > 32) {
            errors.rejectValue("login", "Size.signupForm.username");
        }
        if (user.getEtat().equals(DO_INSERT)){
            if (userService.findByLogin(user.getLogin()) != null) {
                errors.rejectValue("login", "Duplicate.signupForm.username");
            }

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "NotEmpty");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idRole", "NotEmpty");
    }
}
