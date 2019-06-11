package ci.dcg.visionzero.role;

public class RoleForm {

    private String id;
    private String roleName;

    public RoleForm(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleForm() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Role createNewRole(){
        return new Role(getRoleName().toUpperCase());
    }

    public Role updateRole(){
        return new Role(getId(), getRoleName().toUpperCase());
    }
}
