package devstack.userinfo;

import devstack.user.ApplicationUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {

    private final UserAvatarRepository userAvatarRepository;

    public void saveUserAvatar(ApplicationUser user, MultipartFile file) throws IOException {

        userAvatarRepository.deleteByUserId(user.getId());

        UserAvatar avatar = new UserAvatar();
        avatar.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        avatar.setContentType(file.getContentType());
        avatar.setData(file.getBytes());
        avatar.setSize(file.getSize());
        avatar.setUser(user);

        userAvatarRepository.save(avatar);
    }

    public Optional<UserAvatar> getFile(Long id) {
        return userAvatarRepository.findById(id);
    }

    public UserAvatar getUserAvatarByUserId(Long id) {
        return userAvatarRepository.findByUserId(id);
    }

}
