package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.imageuser.ImageUser;
import ci.dcg.visionzero.role.Role;
import org.springframework.web.multipart.MultipartFile;

public class UtilisateurForm {
    private String id;
    private String login;
    private String password;
    private String passwordConfirm;
    private String email;
    private ImageUser imageUser;
    private Integer active;
    private Role role;
    private MultipartFile file;
    private String idRole;
    private String etat;

    public UtilisateurForm() {
        super();
    }

    public UtilisateurForm(String id, String login, String email, String idRole) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.idRole = idRole;
    }

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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ImageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageUser imageUser) {
        this.imageUser = imageUser;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Utilisateur createNewUser(){
        return new Utilisateur(getId(), getLogin(), getPassword(), getPasswordConfirm(), getEmail(), getImageUser(), getRole());
    }

    public Utilisateur updateUser(){
        return new Utilisateur(getId(), getLogin(), getPassword(), getPasswordConfirm(), getEmail(), getActive(), getImageUser(), getRole());
    }
}
