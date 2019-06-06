package ci.dcg.visionzero.role;

import ci.dcg.visionzero.support.ServiceFactory;

import java.util.stream.Stream;

public interface RoleService extends ServiceFactory<Role, String> {
	
	Role findByRoleName(String roleName);

	Stream<Role> getAllRolesStream();

}