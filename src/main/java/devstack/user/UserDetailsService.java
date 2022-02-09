package devstack.user;

import devstack.security.ApplicationUserRole;
import devstack.signup.SignupRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getAllByUsername(s).stream().findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found.", s)));
    }

    public ApplicationUser registerNewUserAccount(SignupRequest signupRequest) throws Exception {

        String username = signupRequest.getUsername().toLowerCase();
        String password = passwordEncoder.encode(signupRequest.getPassword());
        String email = signupRequest.getEmail().toLowerCase();

        if (!userRepository.getAllByUsername(username).isEmpty()) {
            throw new Exception(String.format("The user \"%s\" already exists!", username));
        }

        ApplicationUser user = new ApplicationUser(username, password, email);
        user.setGrantedAuthorities(ApplicationUserRole.STUDENT.getGrantedAuthorities());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        return userRepository.save(user);
    }
}
