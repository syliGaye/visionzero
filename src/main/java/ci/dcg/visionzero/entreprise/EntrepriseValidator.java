package ci.dcg.visionzero.entreprise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class EntrepriseValidator implements Validator {
    @Autowired
    private EntrepriseService entrepriseService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Entreprise.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EntrepriseForm entrepriseForm = (EntrepriseForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomEntreprise", "NotEmpty");
        if (entrepriseForm.getEtat().equals(DO_INSERT)){
            if (entrepriseService.findByNomEntreprise(entrepriseForm.getNomEntreprise()) != null) {
                errors.rejectValue("nomEntreprise", "Duplicate.entrepriseForm.nomEntreprise");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPays", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idSecteurActivite", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idRaisonSociale", "NotEmpty");
    }
}
