package devstack.signup;


import devstack.user.ApplicationUser;
import devstack.user.UserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/signup")
@AllArgsConstructor
public class SignupController {

    private final UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<ApplicationUser> signUp(@RequestBody SignupRequest signupRequest) {

        try {
            ApplicationUser user = userDetailsService.registerNewUserAccount(signupRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
