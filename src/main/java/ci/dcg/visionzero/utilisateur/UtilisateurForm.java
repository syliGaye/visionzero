package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.role.Role;
import org.springframework.web.multipart.MultipartFile;

public class UtilisateurForm {
    private String id;
    private String login;
    private String password;
    private String email;
    private Role role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
