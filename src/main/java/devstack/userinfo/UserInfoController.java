package devstack.userinfo;

import devstack.user.ApplicationUser;
import devstack.user.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping("/info")
@AllArgsConstructor
public class UserInfoController {

    private final UserDetailsService userDetailsService;
    private final FileService fileService;

    @GetMapping("/user")
    @ResponseBody
    public ResponseEntity<UserInfoResponse> currentUserName(Principal principal) {
        ApplicationUser user = (ApplicationUser) userDetailsService.loadUserByUsername(principal.getName());
        UserInfoResponse response = new UserInfoResponse(user.getUsername(), user.getEmail(), user.isEnabled());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/image")
    @ResponseBody
    public ResponseEntity<String> uploadUserAvatar(Principal principal, @RequestParam("image") MultipartFile file) {
        ApplicationUser user = (ApplicationUser) userDetailsService.loadUserByUsername(principal.getName());

        try {
            fileService.saveUserAvatar(user, file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/image")
    public ResponseEntity<byte[]> getAvatar(Principal principal) {
            ApplicationUser user = (ApplicationUser) userDetailsService.loadUserByUsername(principal.getName());
            UserAvatar avatar = fileService.getUserAvatarByUserId(user.getId());

            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type", avatar.getContentType())
                    .body(avatar.getData());
    }

}
