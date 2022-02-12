package devstack.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    List<ApplicationUser> getAllByUsername(String username);
    ApplicationUser getApplicationUserByUsername(String username);
}
