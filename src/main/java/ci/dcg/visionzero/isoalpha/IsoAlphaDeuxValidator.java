package ci.dcg.visionzero.isoalpha;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IsoAlphaDeuxValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        IsoAlphaDeuxForm alphaDeuxForm = (IsoAlphaDeuxForm) o;
    }
}
