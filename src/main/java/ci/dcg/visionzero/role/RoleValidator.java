package ci.dcg.visionzero.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RoleValidator implements Validator {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RoleForm roleForm = (RoleForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "NotEmpty");
        if (roleForm.getRoleName().length() < 5) {
            errors.rejectValue("roleName", "Size.roleForm.roleName");
        }
        if (roleService.findByRoleName(roleForm.getRoleName()) != null) {
            errors.rejectValue("roleName", "Duplicate.roleForm.roleName");
        }
    }
}
