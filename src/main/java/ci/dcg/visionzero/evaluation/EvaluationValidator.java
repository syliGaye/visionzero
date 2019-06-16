package ci.dcg.visionzero.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class EvaluationValidator implements Validator {

    @Autowired
    private EvaluationService evaluationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Evaluation.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EvaluationForm evaluationForm = (EvaluationForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleEvaluation", "NotEmpty");
        if (evaluationForm.getEtat().equals(DO_INSERT)){
            if (evaluationService.findByLibelleEvaluation(evaluationForm.getLibelleEvaluation()) != null) {
                errors.rejectValue("libelleEvaluation", "Duplicate.evaluationForm.libelleEvaluation");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idAxe", "NotEmpty");
    }
}
