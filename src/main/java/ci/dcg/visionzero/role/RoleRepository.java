package ci.dcg.visionzero.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query("select role from Role role where role.roleName = :roleName")
    Role findByRoleName(@Param("roleName") String roleName);

    @Query("select role from Role role")
    Stream<Role> getAllRolesStream();// Java8 Stream
}
