package ci.dcg.visionzero.secteuractivite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SecteurActiviteValidator implements Validator {

    @Autowired
    private SecteurActiviteService secteurActiviteService;

    @Override
    public boolean supports(Class<?> aClass) {
        return SecteurActivite.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SecteurActiviteForm secteurActiviteForm = (SecteurActiviteForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleSecteurActivite", "NotEmpty");
        if (secteurActiviteService.findByLibelleSecteurActivite(secteurActiviteForm.getLibelleSecteurActivite()) != null) {
            errors.rejectValue("libelleSecteurActivite", "Duplicate.secteurActiviteForm.libelleSecteurActivite");
        }
    }
}
