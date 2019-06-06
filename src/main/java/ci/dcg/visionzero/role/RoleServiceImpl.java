package ci.dcg.visionzero.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Stream<Role> getAllRolesStream() {
		return roleRepository.getAllRolesStream();
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	@Override
	public Role getOne(String s) {
		return roleRepository.getOne(s);
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public int count() {
		return this.findAll().size();
	}

	@Override
	public Role save(Role role) {
		role.setId(this.retourneId());
		return roleRepository.save(role);
	}

	@Override
	public void update(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void delete(String s) {
		roleRepository.deleteById(s);
	}

	@Override
	public boolean isExiste(String s) {
		return roleRepository.existsById(s);
	}

	@Override
	public String retourneId() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

		int i = this.count() + 1;

		return "rol"+ft.format(date)+""+i;
	}
}