package ci.dcg.visionzero.axe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class AxeValidator implements Validator {

    @Autowired
    private AxeService axeService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Axe.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AxeForm axeForm = (AxeForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleAxe", "NotEmpty");
        if (axeForm.getEtat().equals(DO_INSERT)){
            if (axeService.findByLibelleAxe(axeForm.getLibelleAxe()) != null) {
                errors.rejectValue("libelleAxe", "Duplicate.axeForm.libelleAxe");
            }
        }

    }
}
