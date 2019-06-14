package ci.dcg.visionzero.pays;

import ci.dcg.visionzero.isoalpha.IsoAlphaDeuxService;
import ci.dcg.visionzero.isoalpha.IsoAlphaTroisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class PaysValidator implements Validator {

    @Autowired
    private PaysService paysService;

    @Autowired
    private IsoAlphaTroisService isoAlphaTroisService;

    @Autowired
    private IsoAlphaDeuxService isoAlphaDeuxService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Pays.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PaysForm paysForm = (PaysForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libellePays", "NotEmpty");

        if (paysForm.getEtat().equals(DO_INSERT)){
            if (paysService.findByLibellePays(paysForm.getLibellePays()) != null) {
                errors.rejectValue("libellePays", "Duplicate.paysForm.nom");
            }

            if (isoAlphaTroisService.findByCodeIsoAlphaTrois(paysForm.getIsoAlphaTrois()) != null) {
                errors.rejectValue("isoAlphaTrois", "Duplicate.alphaTroisForm.code");
            }

            if (isoAlphaDeuxService.findByCodeIsoAlphaDeux(paysForm.getIsoAlphaDeux()) != null) {
                errors.rejectValue("isoAlphaDeux", "Duplicate.alphaDeuxForm.code");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isoAlphaTrois", "NotEmpty");
        if (paysForm.getIsoAlphaTrois().length() != 3) {
            errors.rejectValue("isoAlphaTrois", "Size.alphaTroisForm.code");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isoAlphaDeux", "NotEmpty");
        if (paysForm.getIsoAlphaDeux().length() != 2) {
            errors.rejectValue("isoAlphaDeux", "Size.alphaDeuxForm.code");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idContinent", "NotEmpty");
    }
}
