package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.imageuser.ImageUser;
import ci.dcg.visionzero.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Value;
import lombok.experimental.NonFinal;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Utilisateur implements Serializable {
    @Id
    @Column(name = "USER_ID", updatable = false, nullable = false)
    private String id;

    @Column(name = "LOGIN", unique=true, nullable=false)
    private String login;

    @JsonIgnore
    @Column(name = "USER_PASSWORD", nullable=false)
    private String password;

    @JsonIgnore
    @Column(name = "USER_PASSWORD_CONFIRM", nullable=false)
    private String passwordConfirm;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Column(name = "USER_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "USER_ACTIVE", nullable=false)
    private Integer active;

    @JsonManagedReference
    @JoinColumn(name = "CODE_IMAGE_USER", referencedColumnName = "CODE_IMAGE_USER")
    @OneToOne(optional = false)
    private ImageUser imageUser;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
    private Role role;

    public Utilisateur() {
        super();
    }

    public Utilisateur(String login, String password, Integer active) {
        this.login = login;
        this.password = password;
        this.active = active;
    }

    public Utilisateur(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public Utilisateur(String login) {
        this.login = login;
    }

    public Utilisateur(String login, String password, String passwordConfirm, String email) {
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
    }

    public Utilisateur(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Utilisateur(String login, String password, String passwordConfirm, String email, Integer active, ImageUser imageUser, Role role) {
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.active = active;
        this.imageUser = imageUser;
        this.role = role;
    }

    public Utilisateur(String id, String login, String password, String passwordConfirm, String email, Integer active, ImageUser imageUser, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.active = active;
        this.imageUser = imageUser;
        this.role = role;
    }

    public Utilisateur(String login, String email, ImageUser imageUser) {
        this.login = login;
        this.email = email;
        this.imageUser = imageUser;
    }

    public Utilisateur(String login, String password, String passwordConfirm, String email, Integer active) {
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.active = active;
    }

    public Utilisateur(String id, String login, String password, String passwordConfirm, String email, ImageUser imageUser, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.imageUser = imageUser;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public ImageUser getImageUser() {
        return imageUser;
    }

    public void setImageUser(ImageUser imageUser) {
        this.imageUser = imageUser;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", login=" + login + ", password=XXXXXXX, active=" + active + "" +
                ", role="+ role + ", image=" + imageUser + "" +
                "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((active == null) ? 0 : active.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        if (active == null) {
            if (other.active != null)
                return false;
        } else if (!active.equals(other.active))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }
}
