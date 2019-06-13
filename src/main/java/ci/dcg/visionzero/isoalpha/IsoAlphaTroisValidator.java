package ci.dcg.visionzero.isoalpha;

import ci.dcg.visionzero.utilisateur.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class IsoAlphaTroisValidator implements Validator {

    @Autowired
    private IsoAlphaTroisService isoAlphaTroisService;

    @Override
    public boolean supports(Class<?> aClass) {
        return IsoAlphaTrois.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        IsoAlphaTroisForm alphaTroisForm = (IsoAlphaTroisForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codeIsoAlphaTrois", "NotEmpty");
        if (alphaTroisForm.getCodeIsoAlphaTrois().length() != 3) {
            errors.rejectValue("codeIsoAlphaTrois", "Size.alphaTroisForm.code");
        }
        if (alphaTroisForm.getEtat().equals(DO_INSERT)){
            if (isoAlphaTroisService.findByCodeIsoAlphaTrois(alphaTroisForm.getCodeIsoAlphaTrois()) != null) {
                errors.rejectValue("codeIsoAlphaTrois", "Duplicate.alphaTroisForm.code");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idPays", "NotEmpty");
    }
}
