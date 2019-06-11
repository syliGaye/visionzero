package ci.dcg.visionzero.signup;

import ci.dcg.visionzero.utilisateur.Utilisateur;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SignupForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
    private static final String EMAIL_MESSAGE = "{email.message}";

    @NotNull(message = SignupForm.NOT_BLANK_MESSAGE)
    private String login;

    @NotNull(message = SignupForm.NOT_BLANK_MESSAGE)
    @Email(message = SignupForm.EMAIL_MESSAGE)
    private String email;

    @NotNull(message = SignupForm.NOT_BLANK_MESSAGE)
    private String password;

    @NotNull(message = SignupForm.NOT_BLANK_MESSAGE)
    private String passwordConfirm;

    private MultipartFile file;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Utilisateur createSuperAdmin() {
        return new Utilisateur(getLogin(), getPassword(), getPasswordConfirm(), getEmail());
    }

    @Override
    public String toString() {
        return "SignupForm{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }
}
