package devstack.userinfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/info")
public class UserInfoController {

    @GetMapping("/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
