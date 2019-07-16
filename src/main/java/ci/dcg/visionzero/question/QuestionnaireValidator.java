package ci.dcg.visionzero.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static ci.dcg.visionzero.web.WebViewName.DO_INSERT;

@Component
public class QuestionnaireValidator implements Validator {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Questionnaire.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        QuestionnaireForm questionnaireForm = (QuestionnaireForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelleQuestionnaire", "NotEmpty");
        if (questionnaireForm.getEtat().equals(DO_INSERT)){
            if (questionnaireService.findByLibelleQuestionnaire(questionnaireForm.getLibelleQuestionnaire()) != null) {
                errors.rejectValue("libelleQuestionnaire", "Duplicate.questionnaireForm.libelleQuestionnaire");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codeEvaluation", "NotEmpty");
    }
}
