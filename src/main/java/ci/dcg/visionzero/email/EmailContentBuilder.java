package ci.dcg.visionzero.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public EmailContentBuilder(@Qualifier("templateEngine") TemplateEngine templateEngine1){
        this.templateEngine = templateEngine1;
    }

    public String build(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("email/emailtemplate", context);
    }
}
