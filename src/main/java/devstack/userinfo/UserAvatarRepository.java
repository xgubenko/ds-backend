package devstack.userinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserAvatarRepository extends JpaRepository<UserAvatar, Long> {

    @Transactional
    UserAvatar findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long usedId);
}