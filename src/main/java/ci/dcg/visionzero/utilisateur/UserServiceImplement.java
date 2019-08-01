package ci.dcg.visionzero.utilisateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Service(value="userService")
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Utilisateur getOne(String s) {
        return userRepository.getOne(s);
    }

    @Override
    public List<Utilisateur> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int count() {
        return this.findAll().size();
    }

    @Override
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@Secured("ROLE_ADMIN")
    public Utilisateur save(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur.setPasswordConfirm(passwordEncoder.encode(utilisateur.getPasswordConfirm()));
        utilisateur.setActive(0);
        return userRepository.save(utilisateur);
    }

    @Override
    public void update(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = userRepository.save(utilisateur);
        System.out.println(utilisateur1.toString());
    }

    @Override
    public void delete(String s) {
        userRepository.deleteById(s);
    }

    @Override
    public boolean isExiste(String s) {
        return userRepository.existsById(s);
    }

    @Override
    public String retourneId() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmmssSS");

        int i = this.count() + 1;

        return "user"+ft.format(date)+""+i;
    }

}
