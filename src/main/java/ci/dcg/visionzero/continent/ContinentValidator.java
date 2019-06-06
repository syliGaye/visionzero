package ci.dcg.visionzero.continent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ContinentValidator implements Validator {

    @Autowired
    ContinentService continentService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Continent.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Continent continent = (Continent) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleContinent", "NotEmpty");
        if (continentService.findByLibelleContinent(continent.getLibelleContinent()) != null) {
            errors.rejectValue("libelleContinent", "Duplicate.saveContinent.libelleContinent");
        }
    }
}
