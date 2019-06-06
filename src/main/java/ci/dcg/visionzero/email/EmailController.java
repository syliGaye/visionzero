package ci.dcg.visionzero.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    @GetMapping("email")
    public String mail() {
        return "email/emailtemplate";
    }

}
