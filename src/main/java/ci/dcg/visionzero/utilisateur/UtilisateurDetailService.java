package ci.dcg.visionzero.utilisateur;

import ci.dcg.visionzero.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class UtilisateurDetailService implements UserDetailsService {

    @Autowired private UserRepository userRepository;

    @Autowired private EmailService emailService;

    public Utilisateur putUtilisateurActive(Utilisateur utilisateur) {
        utilisateur.setActive(1);
        return userRepository.save(utilisateur);
    }

    public void loginUser(Utilisateur utilisateur) {
        emailService.prepareAndSend(utilisateur.getEmail(), "GAYE Mehibo", "TEST");
        SecurityContextHolder.getContext().setAuthentication(authenticate(utilisateur));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur utilisateur = userRepository.findByLogin(s);

        if (utilisateur == null){
            throw new UsernameNotFoundException("user not found");
        }

        Utilisateur utilisateur1 = this.putUtilisateurActive(utilisateur);

        return loadUtilisateur(utilisateur1);
    }

    private Authentication authenticate(Utilisateur utilisateur) {
        return new UsernamePasswordAuthenticationToken(loadUtilisateur(utilisateur), null, Collections.singleton(createAuthority(utilisateur)));
    }

    private User loadUtilisateur(Utilisateur utilisateur) {
        return new User(utilisateur.getLogin(), utilisateur.getPassword(), Collections.singleton(createAuthority(utilisateur)));
    }

    private GrantedAuthority createAuthority(Utilisateur utilisateur) {
        return new SimpleGrantedAuthority(utilisateur.getRole().getRoleName());
    }
}
