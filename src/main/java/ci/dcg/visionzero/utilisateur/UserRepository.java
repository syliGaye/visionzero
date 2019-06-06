package ci.dcg.visionzero.utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Utilisateur, String> {

    @Query("SELECT u FROM Utilisateur u WHERE u.login = :login")
    Utilisateur findByLogin(@Param("login") String login);

    @Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
    Utilisateur findByEmail(@Param("email") String email);
}
