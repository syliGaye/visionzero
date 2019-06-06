package ci.dcg.visionzero.role;

public class RoleForm {

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role createNewRole(){
        return new Role(getRoleName());
    }
}
