package ci.dcg.visionzero.role;

import ci.dcg.visionzero.utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable{

    private static final long serialVersionUID = 2284252532274015507L;

    @Id
    @Column(name = "ROLE_ID", updatable = false, nullable = false)
    private String id;

    @Column(name="ROLE_NAME", unique = true, nullable = false)
    private String roleName;

    @JsonBackReference
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Set<Utilisateur> utilisateurList;

    public Role(){
        super();
    }

    public Role(String roleName){
        super();
        this.roleName = roleName;
    }

    public Role(String id, String roleName) {
        super();
        this.id = id;
        this.roleName = roleName;
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

    @XmlTransient
    public Set<Utilisateur> getUtilisateurList() {
        return utilisateurList;
    }

    public void setUtilisateurList(Set<Utilisateur> utilisateurList) {
        this.utilisateurList = utilisateurList;
    }

    @Override
    public String toString() {
        return "Role [id=" + id + ", role=" + roleName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    public int compareTo(Role role){
        return this.roleName.compareTo(role.getRoleName());

    }
}