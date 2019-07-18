package ci.dcg.visionzero.reponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class ReponseValidator implements Validator {
    @Autowired
    private ReponseService reponseService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Reponse.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ReponseForm reponseForm = (ReponseForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleReponse", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valeurReponse", "NotEmpty");
        if (reponseForm.getEtat().equals(DO_INSERT)){
            if (reponseService.findByLibelle(reponseForm.getLibelleReponse()) != null) {
                errors.rejectValue("libelleReponse", "Duplicate.reponseForm.libelleReponse");
            }

            if (reponseService.findByValeur(reponseForm.getValeurReponse()) != null) {
                errors.rejectValue("valeurReponse", "Duplicate.reponseForm.valeurReponse");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idCouleur", "NotEmpty");
    }
}
