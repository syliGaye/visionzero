package ci.dcg.visionzero.raisonsociale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RaisonSocialeValidator implements Validator {
    @Autowired
    private RaisonSocialeService raisonSocialeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RaisonSociale.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RaisonSocialeForm raisonSocialeForm = (RaisonSocialeForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleRaisonSociale", "NotEmpty");
        if (raisonSocialeService.findByLibelleRaisonSociale(raisonSocialeForm.getLibelleRaisonSociale()) != null) {
            errors.rejectValue("libelleRaisonSociale", "Duplicate.raisonSocialeForm.libelle");
        }
    }
}
