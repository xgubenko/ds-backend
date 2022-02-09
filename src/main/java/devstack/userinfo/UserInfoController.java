package devstack.userinfo;

import devstack.user.ApplicationUser;
import devstack.user.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/info")
@AllArgsConstructor
public class UserInfoController {

    private final UserDetailsService userDetailsService;

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<UserInfoResponse> currentUserName(Principal principal) {
        ApplicationUser user = (ApplicationUser) userDetailsService.loadUserByUsername(principal.getName());
        UserInfoResponse response = new UserInfoResponse(user.getUsername(), user.getEmail(), user.isEnabled());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
